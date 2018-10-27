/*
 * TreeVerticalOrderTraversal.cpp
 *
 *  Created on: Oct 18, 2018
 *      Author: sisba01
 */

#include<iostream>
#include<cstring>
#include<bits/stdc++.h>
#include<vector>

using namespace std;

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

 void verticalOrderUtil(TreeNode *root, map<int, vector<int> > &m, int index) {
     if(!root)
         return;
     m[index].push_back(root->val);
     verticalOrderUtil(root->left, m, index-1);
     verticalOrderUtil(root->right, m, index+1);
 }

 vector<vector<int> > verticalOrderTraversal(TreeNode* A) {
     map<int, vector<int> > m;
     int index = 0;
     verticalOrderUtil(A, m, index);
     vector<vector<int> > list(m.size());
     map< int, vector<int> > :: iterator it;
     int i = 0;
     for(it=m.begin();it!=m.end();it++) {
         list[i++] = it->second;
     }
     return list;
 }

int main() {
	TreeNode * root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->right->left = new TreeNode(4);
	root->right->left->right = new TreeNode(5);
	vector<vector<int> > out = verticalOrderTraversal(root);
	for(vector<vector<int> > :: const_iterator i = out.begin();i!=out.end();i++) {
		cout<<"[";
		for(vector<int> :: const_iterator j=i->begin();j!=i->end();j++) {
			cout<<*j;
		}
		cout<<"]"<<endl;
	}
	return 0;
}



