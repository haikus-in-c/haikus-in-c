/* Correct Solution to Spotify Cat vs Dog problem
 * Modelled solution using bipartite graph matching 
 * Using algorithms intended for max flow
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct voter {
	int  like;
	int  hate;
}; 

int dfs(int u,int dogs_filled, int* match, int* visited, int** edge_array); 
int main(int argc, char** argv) {
	int times, i,j,k, cats, dogs, voters, prefer, reject, edge_filled;
	int cats_filled;
	int dogs_filled;
	char buf[256];
	char type;
	char* pch;
	struct voter* cat_voters, *dog_voters, *current;
	int ** edge_array, *visited, *match;
	scanf("%d", &times);
	for(j = 0; j< times; j++) {
		scanf("\n");
		pch =fgets(buf, 256,stdin);  
		sscanf(buf," %d %d %d", &cats, &dogs, &voters);  
		cat_voters  = (struct voter*) malloc(voters * sizeof(struct voter));
		dog_voters = (struct voter*) malloc (voters * sizeof(struct voter));
		cats_filled =0;
		dogs_filled =0;
		for ( i = 0; i< voters; i++) {	
			pch = fgets(buf, 256, stdin);
		        type = *buf;
			pch = strtok(buf, " ");
                        pch = strtok(NULL, " ");
                        prefer = (atoi(buf + 1));
                        reject = atoi(pch + 1);

			if (type == 'C' || type == 'c' )
				current = cat_voters + cats_filled++;
			else if (type == 'D' || type =='d')
				current = dog_voters + dogs_filled++;
			current->like = prefer;	
			current->hate = reject;
		}
		edge_array = (int**) malloc(cats_filled * sizeof(int *));
		
		for(i = 0; i< cats_filled; i++){
			edge_array[i]  = (int*) malloc (dogs_filled * sizeof(int));
			for (k = 0; k< dogs_filled; k++) {
				edge_array[i][k] = -1;
			} 
			edge_filled = 0;
			for(k = 0; k<dogs_filled; k++){
				if(cat_voters[i].like == dog_voters[k].hate || cat_voters[i].hate == dog_voters[k].like) {    
					edge_array[i][k] = k;
				}
			}
		}

		match = (int*) malloc(voters* sizeof(int));
		visited = (int*) malloc(cats_filled * sizeof(int));
		memset(match, 0xffff, voters*sizeof(int));
		for(i = 0; i< cats_filled; i++) {
			memset(visited, 0, cats_filled*sizeof(int));
			dfs(i, dogs_filled, match, visited, edge_array);
		}

		int ans = 0;
		for(i =0; i<dogs_filled; i++) {
			ans+= (match[i] != -1); 
		}

		printf("%d\n", (voters-ans), sizeof(int));
 
		for(i=0; i<cats_filled; i++) {
			free(*(edge_array+i));
		}
		free(edge_array);
		free( match);
		free(visited);
		free(cat_voters);
		free(dog_voters);
	}
}

int dfs(int u,int dogs_filled, int* match, int* visited, int** edge_array) {

	if(*(visited + u)) 
		return 0;
	*(visited + u)= 1;
	int i;
	for(i =0; i<dogs_filled; i++) {
		if(*(*(edge_array+u)+ i) != -1 && (match[edge_array[u][i] ] == -1 || dfs(match [ edge_array[u][i]], dogs_filled, match, visited, edge_array ))) {
			match[ edge_array[u][i] ] = u;
			return 1;
		}
	}


	return 0;
} 
