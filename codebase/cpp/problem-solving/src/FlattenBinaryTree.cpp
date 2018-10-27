/*
 * FlattenBinaryTree.cpp
 *
 *  Created on: Oct 19, 2018
 *      Author: sisba01
 */

#include<iostream>
#include<cstring>
#include<bits/stdc++.h>
#include<vector>

using namespace std;

// Definition for binary tree
 struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 };

 TreeNode * newTreeNode(int d) {
 	 TreeNode * tNode = (struct TreeNode*)malloc(sizeof(TreeNode));
 	 tNode->val = d;
 	 tNode->left = tNode->right = NULL;
 	 return tNode;
  }

void flattenUtil(TreeNode * root, TreeNode ** curNode) {
    if(!root) return;
    TreeNode * ln = root->left;
    TreeNode * rn = root->right;

    root->left = NULL;
    if(*curNode) {
        (*curNode)->right = root;
    }
    *curNode = root;

    flattenUtil(ln, curNode);
    flattenUtil(rn, curNode);
}

TreeNode* flatten(TreeNode* A) {
    // Do not write main() function.
    // Do not read input, instead use the arguments to the function.
    // Do not print the output, instead return values as specified
    // Still have a doubt. Checkout www.interviewbit.com/pages/sample_codes/ for more details

    TreeNode * curNode = NULL;
    flattenUtil(A, &curNode);

    return A;

}

int main() {
	TreeNode * root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->right->left = new TreeNode(4);
	root->right->left->right = new TreeNode(5);

	TreeNode * head = flatten(root);

	while(head) {
		cout<<head->val<<" ";
		head = head->right;
	}

	return 0;
}



