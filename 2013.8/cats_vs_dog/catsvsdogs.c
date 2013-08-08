/*CatDog Solution {Incorrect} by Ruchir Khaitan - student at Columbia University
 *Hi, I hope you like this, also hope this works 
 *Simulated a game by killing the least supported pets off first, to see the point 
* where satisfaction peaks
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct pet {
	int fitness; //running tally of upvotes - downvotes
	int contending; //whether or not this pet is still  competing
};

struct voter {
	//pet they kept
	struct pet* keep;
	struct pet* reject;   
}; 

int sim_game(struct voter* voter_array, int voters, struct pet* cat_array, int cats, struct pet* dog_array, int dogs);

struct pet* find_min(struct pet* cat_array, int cats, struct pet* dog_array, int dogs); 
int main(int argc, char** argv) {
	int times, i,j, cats, dogs, voters, prefer, reject;
	char buf[256];
	char type;
	char* pch;
	struct voter* v_array, *current;
	struct pet* cat_array, *dog_array, *curr;
	scanf("%d", &times);
	for(j = 0; j< times; j++) {
		scanf("\n");
		pch =fgets(buf, 256,stdin);  
		sscanf(buf," %d %d %d", &cats, &dogs, &voters);  
		v_array   = (struct voter *) malloc(voters * sizeof(struct voter));
		cat_array = (struct pet*) malloc (cats * sizeof(struct pet));
		dog_array = (struct pet*) malloc (dogs * sizeof (struct pet));
		//initialize values in structs 
		for(i = 0; i< cats; i++) {
			curr = cat_array+ i;
        		curr-> fitness = 0;
			curr-> contending = 1;
		}	
		for(i = 0; i< dogs; i++) {
                	curr = dog_array+ i;
			curr->fitness = 0;
			curr->contending = 1;
        	}
		//input code to register voter preferences
		for ( i = 0; i< voters; i++) {
			pch = fgets(buf, 256, stdin);
			type = *buf; 
			current= v_array + i;
	
			pch = strtok(buf, " ");
	     		pch = strtok(NULL, " "); 
	  		prefer = (atoi(buf + 1)) -1;
			reject = (atoi(pch + 1)) -1;
			if (type == 'C'|| type == 'c') {
				current->keep = (cat_array + prefer);
				current->reject = (dog_array + reject);
			}
			else { 
				current->keep = (dog_array + prefer);
				current->reject = (cat_array + reject); 
			}
			current->keep->fitness++;
			current->reject->fitness--;
		}

		/* steps: find weakest- lowest contending fitness across both across both lists
		 *remove him - set contending to zero  
	         *update satisfied - meaning pass through voter array, 
		 *satisfied if keep is contending, reject is not
	         *continue updating till only 1 pet remains, return max satisfaction number 
		 */
			int satisfied = sim_game(v_array, voters, cat_array, cats, dog_array, dogs);
			printf("%d\n", satisfied);
			free(v_array); free(cat_array); free(dog_array); 
	}
}
 
struct pet* find_min(struct pet* cat_array, int cats, struct pet* dog_array, int dogs) {
	int min = 1000; 
	int i = 0;
	struct pet* min_addr;
	struct pet* curr;
	for(i = 0; i< cats; i++) {
		curr = cat_array+ i;
		if(curr ->fitness < min && curr->contending != 0 ) {
			min = curr->fitness;
			min_addr = curr;
		}
	}

	
        for(i = 0; i< dogs; i++) {
		curr = dog_array + i;
                if(curr->fitness< min && curr->contending != 0) {
                        min = curr ->fitness;
                        min_addr = curr;
                }
        }
	min_addr->contending =  0;   
	return min_addr;	
} 

int sim_game(struct voter* voter_array, int voters, struct pet* cat_array, int cats, struct pet* dog_array, int dogs) {
	/* Simulates game until winner, noting point when the max users were satisfied
	 * I nterpreted max satisfied as "max satisfied at any point in the game" 
	 * Most likely this would be a simple curve with one maxima that it either attains 
	 * at the end - meaning there was one super popular pet or in the middle of the game 
	 * and then always decline - if multiple pets were equally heavily favored 
	 * however, in order to cover all case I continued the simulation til 1 pet won 
	 * to cover all my bases
	 */ 
	int num_satisfied = 0; 
	int max_satisfied = 0;
	int pets_remain = cats + dogs - 1;
	int i;
	struct pet* weakest;

	while(pets_remain){
		weakest = find_min(cat_array, cats, dog_array, dogs); 
		for(i = 0; i< voters; i++) {
			if (voter_array[i].keep->contending && !(voter_array[i].reject->contending)) {
				num_satisfied++;
			} 
		}
		if(num_satisfied> max_satisfied){ 
			max_satisfied = num_satisfied;
		}
		if(pets_remain != 1) {
			num_satisfied = 0;
		}
		pets_remain--;
	}
	return max_satisfied;   
} 

 

