#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define max 1000000000

typedef struct node{ //struct to store information
  long long int winner, skill1, skill2, table;
  struct node *left;
  struct node *right;
} node;

int fight = 0;

node *addNode(node *root, int val){ //create and add nodes to BST
  if (root == NULL){ //if BST is empty
    node *newNode = calloc(1, sizeof(node)); //allocate memory
    //create node
    newNode->table = val;
    newNode->winner = 0;
    newNode->left = NULL;
    newNode->right = NULL;

    return newNode;
  }
  if (val < root->table){ //if value is smaller than existing node
    root->left = addNode(root->left, val); //add to left branch
  }
  if (val > root->table){ //if value is bigger than existing node
    root->right = addNode(root->right, val); //add to right branch
  }
  return root; 
}

void destroyTree(node *root){ //free memory
  if (root == NULL){
    return;
  }
  while (root != NULL){
  //post order
  destroyTree(root->left);
  destroyTree(root->right);
  destroyTree(root);

  free(root); //free memory
  }
}

long long int excitement(node *root, long long int *skill, long long int val){ //function to find and print excitement 
  long long int temp;

  if (root == NULL){ //if BST no longer exists  
    return 0;
  }

  if (root->left == NULL && root->right == NULL){ //if there are no branches
    root->skill1 = skill[fight]; //set skill1 = value of current fight index
    fight++; //increment

    root->skill2 = skill[fight]; //set skill2 = value of current fight index
    fight++; //increment
  }
  if (root->left == NULL || root->right == NULL){ //if there is a smaller or larger branch
    if (root->left == NULL && root->right != NULL){ //if there is only a larger branch
      root->skill1 = skill[fight]; //set skill1 = the current index of fight
      fight++; //increment
      
      val = excitement(root->right, skill, val); //recurse function

      root->skill2 = root->right->winner; //set skill2 = right branch winner value
    }
    if(root->right == NULL && root->left != NULL){ //if there is only a smaller branch

      val = excitement(root->left, skill, val); //recurse function

      root->skill1 = root->left->winner; //set skill1 = left branch winner value
      root->skill2 = skill[fight]; //set skill2 = value of current fight index
      fight++; //increment
    }
  }
  if(root->left != NULL && root->right != NULL){ //if there is a smaller and larger branch
    val = excitement(root->left, skill, val); //recurse function for left branch 
    val = excitement(root->right, skill, val); //recurse function for right branch 

    root->skill1 = root->left->winner; //set skill1 = left branch winner value
    root->skill2 = root->right->winner; //set skill2 = right branch winner value
  }

  if (root->skill1 < root->skill2){ //if left player is smaller than right player
      temp = root->skill2 - root->skill1; //temporary value = right player - left player
      root->winner = root->skill2; //set root node winner value = skill2
    }
  if (root->skill1 > root->skill2){ //if left player is bigger than or equal to right player
      temp = root->skill1 - root->skill2; //temporary value = skill1 - skill2
      root->winner = root->skill1; //set root node winner value = skill1
    }

  val = val + temp; //find sum of excitement

  return val; //return total excitement value
}

/*void postOrder(node * root){
    if(root == NULL) return;

    postOrder(root->left);
    postOrder(root->right);
    printf("%lld ", root->table);
}*/

int main(void) {
  int n;
  long long int  *skill, *table, i, excite, val;
  node *root = NULL;

  val = 0;

  scanf("%d", &n); //scan in number of players
  table = calloc(n - 1, sizeof(long long int)); //allocate memory for table
  skill = calloc(n, sizeof(long long int)); //allocate memory

  for (i = 0; i < n - 1; i++){
    scanf("%lld", &table[i]); //scan in tables
  }
  for (i = 0; i < n; i++){
    scanf("%lld", &skill[i]); //scan skill levels into an array
  }
  for (i = n - 2; i >= 0; i--){
    root = addNode(root, table[i]); //add tables to BST
  }
  //postOrder(root);
  excite = excitement(root, skill, val); //call the excitement function
  printf("%lld\n", excite); //print total excitement value

  return 0;
}