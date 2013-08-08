#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/* Data Structures Implementations - Ruchir Khaitan 
 * Started 7/9/2013
 */

//stack 

struct stack {
	void* stack_array[20];
	int sp;
};

void init_stack(struct stack* stack) {
	memset(stack, 0, sizeof( struct stack));
}

int stack_empty(struct stack* stack) {
	if(stack->sp)
		return 1;
	return 0;
}

int stack_push(struct stack* stack, void* data){
	if(stack->sp<20){
		stack->stack_array[stack->sp++] = data;
		return 0;
	}

	return -1; //Error condition
}

void* stack_pop(struct stack* stack){
	if(stack->sp> 0){
		stack->sp = stack->sp -1;
		return stack->stack_array[stack->sp];
	}
	return NULL;

}
void* stack_top(struct stack* stack){
	return stack->stack_array[stack->sp];
}

//Binary tree using only integer keys/values 
//can generalize to arbitrary void* as well
struct btree_node{
	int value;
	struct btree_node* parent; 
	struct btree_node* left;
	struct btree_node* right;
};
//the below is uneccessary
struct btree{
	struct btree_node* root;
	int height; //possibly remove?
};

void print_rec(struct btree_node* node, int indent){
	int i;
	//for(i = 0; i< indent; i++) {
	//	fprintf(stderr,"_");
	//} 
	if(indent){ 
	printf("Node Value: %d\n", node->value);
	}
	else {
		printf("Root Node Value: %d\n", node->value);
	}
	if(node->left){
		for(i = 0; i<indent; i++){
			printf("   ");
	}
		printf("\\_Left child: ");
		print_rec(node->left, indent+1);
	}
	if(node->right){
		for(i = 0; i<indent; i++) {
			printf("   ");
		}
		printf("\\_Right child: ");
		print_rec(node->right, indent+1);
	}
}

//Binary Search Tree
struct btree_node* create_node(struct btree_node* parent, int x){
	struct btree_node* newNode = (struct btree_node*) malloc(sizeof(struct btree_node));
	newNode->value = x;
	newNode->left = NULL;
	newNode->right = NULL;
	newNode->parent = parent;
	return newNode;
}

struct btree_node* init_tree(int x){
	struct btree_node* newNode = (struct btree_node*) malloc(sizeof(struct btree_node));
	newNode->value = x;
	newNode->left = NULL;
	newNode->right = NULL;
	newNode->parent = NULL;
	return newNode;
}


void insert_node(struct btree_node** node,int x, struct btree_node* parent){
	if(*node == NULL){
		*node = create_node(parent, x);
		return;
	}
	
	if(x<(*node)->value) {
		insert_node(&((*node)->left), x, *node);
		return;
		}

	if(x>(*node)->value) { 
		insert_node(&((*node)->right), x, *node);
		return;
	}
	return;
}

struct btree_node* bst_search(struct btree_node* node, int x){
	if(node==NULL)
		return NULL;
	if(node->value == x)
		return node;
	if(x<node->value)
		return bst_search(node->left, x);

	return bst_search(node->right, x);

}

void free_tree(struct btree_node* node){
	
	if(node == NULL)
		return;
	if(node->left)
		free_tree(node->left);
	if(node->right);
		free_tree(node->right);
	free(node);
	return;
	
}

void inorder_print(struct btree_node* node){
	if(node){
		if(node->left)
			inorder_print(node->left);
		printf("%d\n", node->value);
		if(node->right)
			inorder_print(node->right);
	}
}

//fixed size bst
struct fixed_bst{ 
	//same principle as fixed size heap
	int* tree;
};

struct fixed_bst* init_fixed_bst(int size) {
	struct fixed_bst* new_tree = (struct fixed_bst*) malloc(sizeof(struct fixed_bst));
	new_tree->tree = (int*) calloc(size, sizeof(int));
	new_tree->tree[0] = size;
	return new_tree;
}

void resize_tree(struct fixed_bst* bst){
	printf("Performing resize\n");
	int* new_array = calloc(((bst->tree)[0])*2, sizeof(int));
	int i;
	new_array[0] = ((bst->tree)[0]) *2;
	for(i = 1; i<(bst->tree)[0]; i++){
		new_array[i] = bst->tree[i];
	}

	free(bst->tree);
	bst->tree = new_array;

}

void free_fixed_bst(struct fixed_bst* bst){

	free(bst->tree);
	free(bst);

}

void insert_fixed_bst(struct fixed_bst* bst, int value){
	int index = 1;
	int size = (bst->tree)[0];
	printf("Current size of fixed bst: %d\n", size);
	int* curr = &((bst->tree)[index]);
	while(*curr){
		if(value< *curr)
			index = index* 2;
		if(value > *curr)
			index = 2* index + 1;
		if(index>= size)
			resize_tree(bst);
		curr = &((bst->tree)[index]);
	}
	*curr = value;

}

int search_fixed_bst(struct fixed_bst* bst, int value){
	int index = 1;
	int* curr = &((bst->tree)[index]);
	while(*curr && *curr != value){
		if(value < *curr)
			index = index* 2;
		if(value > *curr)
			index = index* 2 + 1;
		if(index >= (bst->tree)[0]) 
			return 0;
		curr = &((bst->tree)[index]);
	}
	if (value != *curr)
		return 0;

	return *curr;
}

//Balanced BST Red-Black Trees

//Regular red black trees
#define BLACK 0
#define RED 1

struct rb_tree{ 
	int value;
	int color;
	struct rb_tree* parent;
	struct rb_tree* left;
	struct rb_tree* right;
};
struct rb_wrap {
	struct rb_tree* root;
};


struct rb_wrap* init_rb_tree(int val) {
	struct rb_tree* rb_root = (struct rb_tree*) malloc(sizeof(struct rb_tree));
	rb_root->value = val;
	rb_root->color = BLACK; 
	rb_root->parent = NULL;
	rb_root->left = NULL;
	rb_root->right = NULL;
	struct rb_wrap* rb_tree = malloc(sizeof(struct rb_wrap));
	rb_tree->root = rb_root;
	return rb_tree;
}

struct rb_tree* create_node_rb(struct rb_tree* parent, int x){
        struct rb_tree* newNode = (struct rb_tree*) malloc(sizeof(struct rb_tree));
        newNode->value = x;
	newNode->color = RED;
        newNode->left = NULL;
        newNode->right = NULL;
        newNode->parent = parent;
        return newNode;
}
struct rb_tree* get_grandpa(struct rb_tree* node){
	if(node == NULL)
		return NULL;
	if(node->parent == NULL) 
		return NULL;
	return node->parent->parent;

}

struct rb_tree* get_uncle(struct rb_tree* node){
	struct rb_tree* grandparent = get_grandpa(node);
	if(!grandparent)
		return NULL;
	if(grandparent->left == node->parent)
		return grandparent->right;

	return grandparent->left;
}

void left_rotate(struct rb_wrap* rb_tree, struct rb_tree* node){
	if(!node)
		return;
	struct rb_tree* right_child = node->right;
	if(!right_child)
		return;
	//printf("performing left rotate on node value: %d\n", node->value);
	node->right = right_child-> left;
	if(right_child->left) 
		right_child->left->parent = node;
	right_child->parent = node->parent;
	if(node->parent){
		if(node == node->parent->left){
			node->parent->left = right_child;
		}
		else {
			node->parent->right = right_child;
		}
	}
	if(rb_tree->root == node)
		rb_tree->root = right_child;
	
	right_child->left = node;
	node->parent = right_child;
}

void right_rotate(struct rb_wrap* rb_tree, struct rb_tree* node) {
	if(!node)
		return;
	struct rb_tree* left_child = node->left;
	//printf("performing r rotate\n");
	if(!left_child)
		return;
	node->left = left_child->right;
	if(left_child->right)
		left_child->right->parent = node;
	left_child->parent = node->parent;
	if(node->parent) {
		if(node == node->parent->right){
			node->parent->right = left_child;
		}
		else{
			node->parent->left = left_child;
		}
	}
	if(rb_tree->root == node)
		rb_tree->root =left_child;
	left_child->right = node;
	node->parent = left_child;
}

void fix_rb_tree(struct rb_wrap* rb_tree, struct rb_tree* node){
	//printf("top of fix rb tree\n");
	if(!node){ 
		printf("No node\n");
		return;
	}
	if(node == rb_tree->root || !(node->parent)){
		//fprintf(stderr,"Recoloring root node\n");
		node->color = BLACK;
		return;
	}
	if(node->parent->color == BLACK) {
		//fprintf(stderr,"Parent black:OK\n");
		return;
	}
	//fprintf(stderr,"try to get relatives now\n");
       	struct rb_tree* grandpa = get_grandpa(node);
        struct rb_tree* uncle = get_uncle(node);
	//int g_color = grandpa->color;
	int u_color;
	if(uncle){
		u_color =uncle->color;
	}
	else{
		u_color = BLACK; 
        }
	//printf("before main loop node value = %d color = %d\n", node-> value, node->color);
        if(node->parent->color == RED){
		if( u_color == RED){
			//printf("Here in case 1- both p and u red\n");
                	node->parent->color = BLACK;
                	uncle->color = BLACK;
                	grandpa->color = RED;
                	fix_rb_tree(rb_tree, grandpa);
        	}
		else if(u_color == BLACK){
			//printf("here in case 2: u black\n");
			if(node == node->parent->right && (node-> parent)== grandpa->left){
				node = node->parent;
				left_rotate(rb_tree, node);
			}
			else if(node == node->parent->left && node->parent == grandpa->right){
				node = node->parent;
				right_rotate(rb_tree, node);
			}
			node->parent->color = BLACK;
			node->parent->parent->color = RED;
			if(node->parent == node->parent->parent->left)
				right_rotate(rb_tree, node->parent->parent);
			//printf("here at the very end of case 3\n");
				left_rotate(rb_tree, node->parent->parent);
		
		}	 
	}						  
}
insert_node_rb(struct rb_wrap* tree, struct rb_tree** node,int x, struct rb_tree* parent){
        if(*node == NULL){
                *node = create_node_rb(parent, x);
                fix_rb_tree(tree, *node);
                return;
        }

        if(x<(*node)->value) {
                insert_node_rb(tree, &((*node)->left), x, *node);
                return;
                }

        if(x>(*node)->value) {
                insert_node_rb(tree, &((*node)->right), x, *node);
                return;
        }
        return;
}


void insert_rb_tree (struct rb_wrap* rb_tree, int x){
        insert_node_rb(rb_tree, (&(rb_tree->root)), x, NULL);
}




struct rb_tree* search_rb_node(struct rb_tree* node, int x){
        if(node==NULL)
                return NULL;
        if(node->value == x)
                return node;
        if(x<node->value)
                return search_rb_node(node->left, x);

        return search_rb_node(node->right, x);

}
struct rb_tree* search_rb(struct rb_wrap* rb_tree, int x){
        return search_rb_node(rb_tree->root,  x);
}

void free_rb_node(struct rb_tree* node){
	if(node){
		if(node->left)
			free_rb_node(node->left);
		if(node->right)
			free_rb_node(node->right);
		free(node);
	}
	return;
}
void free_rb(struct rb_wrap* rb_tree){
	free_rb_node(rb_tree->root);
	free(rb_tree);
}

void print_rb_node(struct rb_tree* node, int indent){
	int i;
	//for(i = 0; i<indent; i++){
	//	fprintf(stderr, "_" );
	//}
	if(!indent) { printf("Root "); }
	if(node->color) {
		printf("Node value: %d and color: RED\n", node->value);
	}
	else{ 
		printf("Node value: %d and color: BLACK\n", node->value);
	}
	if(node->left){
		for(i = 0; i<=indent; i++){
                	printf("  ");
        	}
		printf("\\_Left child: ");
		print_rb_node(node->left, indent+1);
	}
	if(node->right){
		for(i = 0; i<=indent; i++){
                	printf("  ");
        	}

		printf("\\_Right child: ");
		print_rb_node(node->right, indent+1);
	}
}

void print_rb(struct rb_wrap* rb_tree){
	//printf("is anything on? \n");
	print_rb_node(rb_tree->root, 0);
}


//skip lists 

struct skip_node{
	struct skip_node** array;
	int data;
};

struct skip_list {
	struct skip_node* head;
	int levels;
	int size;	//number inserted
	int curr_level;
};

struct skip_list* init_skip_list( int max_level){
	struct skip_list* list = (struct skip_list*) malloc(sizeof(struct skip_list));
	list->levels = max_level;
	list->size = 0;
	list->head = (struct skip_node*) malloc(sizeof(struct skip_node));
	list->head->data= 0;
	list->head->array = (struct skip_node**) calloc(max_level, sizeof(struct skip_node*));
	int i;
	list->curr_level = 0;
	return list;
}
struct skip_node* create_skip_node(struct skip_list* list, int value, int node_level){
	struct skip_node* newNode = (struct skip_node*) malloc(sizeof(struct skip_node));
	newNode->data = value;
	newNode->array = (struct skip_node**) calloc(node_level, sizeof(struct skip_node*));
	return newNode;

}

int search_skip_list(struct skip_list* list, int value) {
	int i;
	struct skip_node* curr, *prev;
	curr = list->head;
	for(i =list->levels-1; i>= 0; i--){
		while(curr->array[i] !=NULL && curr->array[i]->data <value) {
			curr= curr->array[i];
		}
	}
	curr = curr->array[0]; 
	if(curr && curr->data == value)
		return 1;
	return 0;
}
struct skip_node* insert_skip_list(struct skip_list* list, int value){
	struct skip_node** touched = (struct skip_node**) calloc(list->levels, sizeof(struct skip_node*)); 
	list->size++;
	struct skip_node* curr = list->head;
	int i, node_level;
	node_level = 1;
	for(i = list->levels-1; i>=0; i--){
		while(curr->array[i] != NULL  && curr->array[i]->data < value) {
		//	printf("Curr data in insert %d\n", curr->data);
			curr = curr->array[i];
		}
		touched[i] = curr;
	}
	curr = curr->array[0];
	//printf("Final curr data in insert %d\n", curr->data);
	if(!curr || curr->data != value) {
		int exp = 1;
		int test = list->size;
        	for(i = 1; i<list->levels; i++){
			exp = exp*2;
                	if(exp <= test && test%(exp) == 0){
                        	node_level++;
			//	test = test/2;
                	}
               		 else{
                        	break;
                	}	
        	}	
        	//printf("New node has level: %d\n", node_level);
        	
		struct skip_node* new_node= create_skip_node(list, value, node_level);
		if(node_level> list->curr_level){
			for(i = list->curr_level +1; i< list->levels; i++) {
				touched[i] = list->head;
			}
			list->curr_level = node_level;
		}
		for(i = 0; i< node_level; i++) {
			new_node->array[i] = touched[i]->array[i];
			touched[i]->array[i] = new_node;
		}
	}
	free(touched);
}

void free_skip_nodes(struct skip_node* node) {

	if(node->array[0]) 
		free_skip_nodes(node->array[0]);

	free(node->array);
	free(node);
} 
void free_skip_list(struct skip_list* list) {
	free_skip_nodes(list->head);
	free(list);
}
	
void print_skip_list(struct skip_list* list) {
	int i;
	struct skip_node* curr = list-> head;
	for(i = list->levels -1; i>= 0; i--){
		printf("Level %d: ", i);
		while(curr->array[i] != NULL){
			printf("%d ", curr->array[i]->data);
			curr = curr->array[i];
		}
		printf("NULL\n");
		curr = list->head;
	}
}
