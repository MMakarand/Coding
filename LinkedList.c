/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: makarand
 *
 * Created on 30 April, 2016, 2:17 PM
 */

#include <stdio.h>
#include <stdlib.h>

struct Node {

    int data;
    struct Node *next;
};


void findKey(struct Node **head, struct Node **prev, struct Node **temp, int key )
{
    if (*head == NULL) return;
    
    if((**head).data == key)
    {
        *temp = *head;
        *prev = NULL;
        return;
    }
    
    *temp = *head;
    *prev = NULL;
    
    while(*temp != NULL && (**temp).data != key)
    {
      *prev = *temp;
      *temp =(**temp).next;
    
    }
    
    return;
}

void swap(struct Node **head,int key1, int key2)
{

    struct Node *prev1,*temp1,*next1;
    struct Node *prev2, *temp2,*next2;
    struct Node *temp;
    //Assuming all keys are distinct
    
    if(key1 == key2)
    {
        printf("Both keys are same, failing assumption of distinct keys\n");
        return;
    }
    findKey(head, &prev1, &temp1, key1);
    
    if(temp1 != NULL)
    {
      findKey(head, &prev2, &temp2, key2);
        if(temp2 != NULL)
        {
            next1 = temp1->next;
            next2 =  temp2->next;
            
            //If one Node is head
            if(temp1 == *head)
            {
                printf("First key at head position\n");
                temp2->next = next1;
                *head = temp2;
                prev2->next = temp1;
                temp1->next=next2;
                return;          
            }
            else if(temp2 == *head)
            {
                printf("Second key at head position\n");
                temp1->next = next2;
                *head = temp1;
                prev1->next = temp2;
                temp2->next = next1;
                return;
            }
	  //If both nodes are adjacent

	 else if(temp1->next == temp2)
	   {

		prev1->next = temp2;
		temp =temp2->next;
		temp2->next = temp1;
		temp1->next = temp;
		return;
	}
	else if(temp2->next = temp1)
	{
		prev2->next = temp1;
		temp = temp1->next;
		temp1->next = temp2;
		temp2->next = temp;
		return;
	}
                prev1->next = temp2;
                temp2->next = next1;
                prev2->next= temp1;
                temp1->next = next2;
                return;
        }
      printf("key2 is not found\n");
      return;
    }
    printf("key1 is not found");
   return; 
 }

/*
 *
 */
void push(struct Node **head, int data)
{
    struct Node *newNode = calloc(1, sizeof(struct Node));
    newNode->data = data;
    if(*head)
        newNode->next = *head;
    else
        newNode->next = NULL;
    
    *head =  newNode;
}

void append(struct Node **head, int data)
{
    struct Node *newNode = calloc(1, sizeof(struct Node));
    struct Node *last = *head;
    newNode->data = data;
    newNode->next = NULL;
    
    if(*head == NULL)
    {
        *head = newNode;
        return;
    }
    
    while(last->next)
        last= last->next;
    
    last->next = newNode;
    
    
}

void insertAt(struct Node *prev_Node, int data)
{

    if(prev_Node == NULL)
        return;
    

    struct Node *newNode = calloc(1, sizeof(struct Node));
    newNode->data = data;
    newNode->next = prev_Node->next;
    
    prev_Node->next = newNode;


}

void print_Linked_List(struct Node *head)
{
    printf("\n");

    while(head)
    {
        printf("%d \t",head->data);
        head = head->next;
    
    }


}

/*
 * 
 */
int main(int argc, char** argv) {

    struct Node *head=NULL;
    
    push(&head,5);
    
    push(&head,3);
    
    push(&head,1);
    
    print_Linked_List(head);
    append(&head,7);
    
    append(&head,9);
    
    print_Linked_List(head);
    insertAt(head->next,2);
    print_Linked_List(head);
    
    swap(&head,5,7);
    print_Linked_List(head);
    
    swap(&head,1,7);
    print_Linked_List(head);
    
    swap(&head,100,9);
    print_Linked_List(head);
    
    
    
    return (EXIT_SUCCESS);
}


