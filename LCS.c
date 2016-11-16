#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int isSubSeq(char *X, int hash[])
{

int temp=0;
int j=0;
int i=0;

for(;i<26;i++)
{

	printf("%c --> %d\n", i+97, hash[i]);

}

	for(;temp<strlen(X);temp++)
	{
		j = X[temp] - 97;
		

		if((hash[j] - 1) < 0)
			return 0;
		else
		 	hash[j]--;
	}

return 1;
}


int calSubString(char * X,char *Y)
{

int c,i;
int length = strlen(Y);
int hash[26];
int temp[26];
int count =0;
char *substr =(char *) malloc(sizeof(char)*length);

	if(substr == NULL)
		return -1;

	for(i=0;i<26;i++)
		hash[i] = 0;

	for(c=0;c<strlen(X);c++)
	{
		hash[X[c] - 97]++;
		
	}

	for(i=0;i<26;i++)
	{	

	printf("%c --> %d\n", i+97, hash[i]);

	}
	
	for( c=0; c<length;c++)
	{
		for(i =1;i<=length-c;i++)
		{
			memset(substr, '\0', sizeof(substr));
			memset(temp, '\0', sizeof(temp));
			memcpy(temp, hash,26*sizeof(int));
			strncpy(substr,Y+ c, i);
			 printf("\n %s",substr);
			if(isSubSeq(substr, temp) && strlen(substr) > count)
				count = strlen(substr);
	
		}	
	
	}

return count;
}

int main()
{
char *X = "geeksforgeek";
char *Y = "geek";

printf("%d",calSubString(X, Y));

return 0;

}
