/*
 ============================================================================
 Name        : dec2base.c
 Author      : Ameer Ibrahim Osman
 Version     :
 Copyright   :  ID :260682723
 Description : dec2base
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
int indx=0;

/*Prototyping functions*/
void dec2base (int input, int base, char *string);
void revStr (char* str, int length);
char printValue (int number);

void dec2base (int input, int base, char *string){//decimal to base method
	while (input > 0){
		int r = input % base;
		string[indx] = printValue(r);
		indx++;
		input = input / base;
	}
}
void revStr (char* str, int length){//reverse string method
	for ( int i = 0 ; i < length / 2; i++){
		char temp = str[i];
		str[i] = str[length - 1 - i];
		str [length - 1 - i] =temp;
	}
}
char printValue (int number){//returns the corresponding requested value. E.g 10 = A
	if (number < 10)
		return ((char)(number + '0'));
	else
		return ((char)(number - 10 + 'A'));
}
int main(int argc , char *argv[]) {
	int base,number;
	char buffer[31];		//buffer array to hold values that will be reversed then printed
		sscanf(argv[1], "%d" , &number);  //inputting number value
		if (argv[2] != NULL) {    // error check for base, sets to 2 if not available
			sscanf(argv[2] , "%d" ,&base);
		}
		else{
			base = 2;

		}
		if (number == 0) return 0; //error check
		if (number != 0){
		printf("The base-%d",base);
		printf(" form of ,%d",number);
		printf(" is:");
		dec2base(number, base, buffer);
		//int arrayLength = sizeof( buffer ) / sizeof(buffer[0]); my mistake in first submission, fixed
		revStr(buffer, indx);
		for ( int i =0 ; i < indx ; i++){ //forloop to iterate through array and print values
			printf ("%c",buffer[i]);
		}

		}
	//}

}
