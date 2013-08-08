#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ds.h"

int naive_find_num_unique(int* array, int size){
	int i, j, unique, num;
	num = 0;
	for( i =0; i<size; i++){
		unique = 1;
		for(j=i+1; j<size; j++){
			if(array[i]== array[j])
				unique = 0;
		}
		if(unique)
			num++;
	}
	return num;
}

int find_unique2(int* array, int size) {
	int i, j, num;
	num =0;
	int* bvector = (int*) malloc(size * sizeof(int));
	memset(bvector, 0, size * sizeof(int));
	for(i = 0; i<size; i++){
		if(!bvector[i]){
			num++;
			bvector[i] = 1;
			for(j =i+1; j<size; j++){
				if(array[i] == array[j])
					bvector[j] = 1;
			}
		}
	}
	free(bvector);
	return num;
}

int find_bst(int* array, int size){
	int i, num;
	struct btree_node* root = init_tree(array[0]);
	num = 1;
	for(i=0;i<size; i++) {
		if(!bst_search(root, array[i])){
			num++;
			insert_node(&root, array[i], NULL);
		}
	}
	//print_rec(root, 0);
	free_tree(root);
	return num;
}
int find_fixed_bst(int* array, int size){
	int i, num;
	struct fixed_bst* f_bst= init_fixed_bst(size);
	num = 0;
	for(i = 0; i<size; i++){
		if(!search_fixed_bst(f_bst, array[i])){
			num++;
			insert_fixed_bst(f_bst, array[i]);
		}
		 
	}
	free_fixed_bst(f_bst);
	return num;
}
int find_rb(int* array, int size){
	int i, num;
	struct rb_wrap* tree = init_rb_tree(array[0]);
	num = 1;
	for(i =0; i<size; i++){
		if(!search_rb(tree, array[i])){
			num++;
			//printf("Now inserting: %d\n", array[i]);
			insert_rb_tree(tree, array[i]);
			//printf("Printing intermediate tree-------\n");
			//print_rb(tree);	
		}
	}
	//print_rb(tree);
	free_rb(tree);
	return num;
}
int find_skip_list(int* array, int size){
	int i, j, num;
	i = 1; 
	j = size/2;
	while(j) {
		j = j/2;
		i++;
	}
	printf("List level = %d\n", i);
	struct skip_list* sl = init_skip_list(i);
	num = 0;
	for(i=0; i<size; i++) {
		if(!search_skip_list(sl, array[i])){
			num++;
		//	printf("Now inserting %d\n", array[i]);
			insert_skip_list(sl, array[i]);
		}
	}
	print_skip_list(sl);
	free_skip_list(sl);
	return num;
}	
int main(int argc, char** argv){
	int a[5] = {1, 2, 3, 4, 8};
	int b[10] ={0, 0, 0, 5, 5, 0 ,5,0, 0, 5};

	int num_a = naive_find_num_unique(a, 5);
	int num_b = naive_find_num_unique(b, 10);
	printf("%d %d\n", num_a, num_b);

	//int num2a = find_unique2(a, 5);
	//int num2b = find_unique2(b, 10);

	//printf("%d %d\n", num2a, num2b);
	int c_size = 1000;
	int* c = (int*) malloc(c_size * sizeof(int));
	int i;
	srand(time(NULL));
	for(i=0; i<c_size; i++){
		c[i] = rand()%c_size + 1 ;
		//printf("%d\n", i%13);
	}

	//int num_c = naive_find_num_unique(c, c_size);
	//printf("%d\n", num_c);
//	int num2_c = find_unique2(c, c_size);
//	printf("Bitvector: %d\n", num2_c);
	int num3_c = find_bst(c, c_size);
	printf("BST: %d\n", num3_c);
	//int num4_c = find_fixed_bst(c, c_size);
	//printf("Fixed BST: %d\n", num4_c);

	int num_rb = find_rb(c, c_size);
	printf("RB Tree: %d bst: %d\n", num_rb, num3_c);
	int num_sl = find_skip_list(c, c_size);
	printf("Skip List: %d\n", num_sl);
	free(c);
} 

