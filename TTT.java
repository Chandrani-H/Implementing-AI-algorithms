
/**
 *
 * @author chandrani96
 */
//THIS IS AN AI FOR TIC-TAC-TOE USING MAGIC SQUARE
//A MAGIC SQUARE is a N*N square such that the DIGITS in each vertical, horizontal, and diagonal row add up to the same value
import java.io.*;

class TTT_magic
{
	static int arr[];    //array to store the magic square of size N*N, HERE N=3
	static char brr[];   //array to store characters: Xs and Os in the order that they have been played, simply so that it is easier to display the BOARD
   	static int Player[]; //array to store the moves played by the opponent Player/user 
	static int AI[];     //array to store the moves played by the AI 
	static int p;        //counter for the number of elements in the array Player[], if p=4, there exists 3 elements in the array (1 to 3)
	static int a;        //counter for the number of elements in the array AI[], if a=4, there exists 3 elements in the array (1 to 3)

	public static void main (String[] args) throws java.lang.Exception
	{
			p=1; //array Player[] has no elements initially
			a=1; ////array AI[] has no elements initially
			arr=new int[10];
			brr=new char[10];
			Player=new int[10];
   			AI=new int[10];
   			
			//THIS IS THE MAGIC SQUARE
			arr[1]=4; arr[2]=9; arr[3]=2;
   			arr[4]=3; arr[5]=5; arr[6]=7;
   			arr[7]=8; arr[8]=1; arr[9]=6;
            
   			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            
   			for(int i=0;i<10;i++)
			{
				Player[i]=0;
				AI[i]=0;
                                brr[i]='-'; //initialize all board moves as "-" meaning no move is played here
			}
			
			//ASKING THE USER: Who plays first?
   			System.out.println("CHOOSE :");
			System.out.println("1. Computer goes First");
			System.out.println("2. Human goes First");
			int choice=Integer.parseInt(br.readLine()); //input choice
			
			if(choice==1) //AI plays first
			{
                                go(1,1);                                  //AI plays move 1 on board
                                int b=1;                                  //counter to alternate between AI and opponent's move
                                user_input();                             //user's move
                                System.out.println("My turn !");          //AI's turn
                                if(brr[9]=='-')                           //check if move 9 is empty
                                {go(9,1);}                                //If yes, AI plays 9
                                else if(make_2()!=0)                      
                                { int x=  make_2();                       //else call make_2() which returns 5 or non corner moves 2, 4, 6 or 8
                                  go(x,1);
                                }
                                while(true)                               //loop breaks when one player wins or when it is draw
                                {
                                    
                                    if(b==1)
                                    {
                                        user_input();                     //user's turn
                                        b=b*-1;
                                    }
                                    else                                  //when b=-1, AI's turn
                                    {
                                        System.out.println("My turn !");
                                        if(poss_win(1)!=0)                //check if AI can win
                                        {
                                            int x=poss_win(1);
                                            go(x,1);                      //play that move and win
                                            System.out.println("haha I win !");
                                            System.exit(0);
                                        }
                                        else if(poss_win(2)!=0)           //check if opponent can win
                                        {
                                            int x=poss_win(2);
                                            go(x,1);                      //block opponent's winning move
                                            System.out.println("Gotcha !");//prints "Gotcha!" everytime opponent is blocked
                                        }
                                        else if(make_2()!=0)              //if AI can neither win nor block opponent's move, call make_2() to return 5 or 2, 4, 6 or 8
                                        {
                                            int x=  make_2();
                                            go(x,1);
                                        }
                                        else                             //unreachable case
                                        {
                                            System.out.println("It's going to be a draw !");
                                        }
                                        
                                     b=b*-1;                            //alternating value of b for next player's turn
                                
                                }
                            }
                                
			}
			else //AI plays second
			{
                            user_input();                               //user plays first
                            System.out.println("My turn !");
                            if(make_2()!=0)                             
                                { int x=  make_2();                     //call make_2() which returns 5 or non corner moves 2, 4, 6 or 8
                                  go(x,1);
                                }
                            int b=1;                                    //b=1 cause user's turn next
                                
                            while(true)
                                {
                                    
                                    if(b==1)
                                    {
                                        user_input();                           //user's turn
                                        b=b*-1;
                                    }
                                    else                                        //when b=-1, AI's turn
                                    {
                                        System.out.println("My turn !");
                                        if(poss_win(1)!=0)                      //check if AI can win
                                        {
                                            int x=poss_win(1);
                                            go(x,1);                            //play that move and win
                                            System.out.println("haha I win !");
                                                    break;
                                        }
                                        else if(poss_win(2)!=0)                 //check if opponent can win
                                        {
                                            int x=poss_win(2);
                                            go(x,1);                            //block opponent's winning move
                                            System.out.println("Gotcha !");     //prints "Gotcha!" everytime opponent is blocked
                                        }
                                        else if(make_2()!=0)
                                        {
                                            int x=  make_2();                   //if AI can neither win nor block opponent's move, call make_2() to return 5 or 2, 4, 6 or 8
                                            go(x,1);
                                        }
                                        else                                    //unreachable case
                                        {
                                            System.out.println("It's going to be a draw !");
                                        }
                                        
                                     b=b*-1;                                    //alternating value of b for next player's turn
                                
                                }
                                }
				
			}
			
	}
	
    static void user_input()throws IOException    //user_input() function takes an integer input from the user and plays that move on board as user's move
    {
        BufferedReader sr=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your turn !");
        int user_inp=Integer.parseInt(sr.readLine()); 
        go(user_inp,2);
    }
    
    static void display()                       //display() function converts the array list of moves played by Opponent and AI into character: "X and O" moves and prints
    {
        
        for(int i=1;i<a;i++)
        {
            brr[AI[i]]='X';
        }
        for(int i=1;i<p;i++)
        {
            brr[Player[i]]='O';
        }
        System.out.println(brr[1]+" "+brr[2]+" "+brr[3]);
        System.out.println(brr[4]+" "+brr[5]+" "+brr[6]);
        System.out.println(brr[7]+" "+brr[8]+" "+brr[9]);
        //to check if draw occurs
        int c=0;
        for(int i=1; i<=9; i++)
        {
            if(brr[i]=='X'||brr[i]=='O')
                c++;
        }
        if(c==9)
        {
            System.out.println("Draw!!");
            System.exit(0);
        }
        
        //UNCOMMENT THIS PART OF THE PROGRAM IF YOU WANT TO SEE THE AI[] AND Player[] ARRAY ELEMENTS
        
        /*System.out.println("AI played:");
        for(int i=1;i<a;i++)
        {
            System.out.print(AI[i]+" ");
        }
        System.out.println("  Now Human played:");
        for(int i=1;i<p;i++)
        {
            System.out.print(Player[i]+" ");
        }
        System.out.println();*/
        
    }
    
	static void go(int num, int player) //Player = 1 for AI, Player = 2 for Opponent  //  num = move to be played on board
	{
		if(player==1)      //add to AI's list of moves
		{
			AI[a]=num;
			a++;
			
		}
		else               //add to Opponent's list of moves
		{
			Player[p]=num;
			p++;
		}
                display(); //prints after each move
	}
	
	static int make_2()                //make_2() function should ideally return 5 else if not available, a random non corner move: 2, 4, 6 or 8
	{                                  //here it returns 2 if available, else 4 (if available) else 6 (if available) else 8
		int c=0;
		for(int i=1;i<10;i++)
		{
			if(Player[i]==5 || AI[i]==5)     //check if 5 has been played by AI or Opponent
			c++;
		}

		if(c==0)                             //if not, return 5
		{
			return 5;
		}
		
		else                                 //check which non corner move is available and return
		{
			int a1=0, a2=0, a3=0, a4=0;
			for(int i=1;i<10;i++)
			{
				if(Player[i]==2 || AI[i]==2)
				{
					a1++;
				}
				if(Player[i]==4 || AI[i]==4)
				{
					a2++;
				}
				if(Player[i]==6 || AI[i]==6)
				{
					a3++;
				}
				if(Player[i]==8 || AI[i]==8)
				{
					a4++;
				}
			}
			if(a1==0)
			return 2;
			else if(a2==0)
			return 4;
			else if(a3==0)
			return 6;
			else if(a4==0)
			return 8;

		}
                return 0;
	}
	
	static int poss_win(int player)   //Player = 1 for AI, Player = 2 for Opponent  //returns move to play to win else 0
	{
	    int D=0;
	    int D_pos=0;
		if(player==1)                 //check if computer can win
		{
			for(int i=1; i<a; i++)
			{
			    for(int j=1; j<a; j++)
			    {
			    
                        /*
                        
                        CALCULATES MAGIC_SQUARE_VALUE OF MOVE TO PLAY TO WIN= 15-(SUM OF 2 CO-LINEAR MOVES ON BOARD)
                        SINCE ALL ROWS, COLUMNS AND DIAGONALS SUM UP TO 15 FOR 3*3 MAGIC SQUARE 
                        
                        */
                        
			            D=15-(arr[AI[i]]+arr[AI[j]]);    

			            for(int k=1; k<=9; k++)       //LOOKING FOR MOVE VALUE CORRESPONDING TO THE MAGIC_SQUARE_VALUE CALCULATED, D
			            {
			                if(arr[k]==D)
			                D_pos=k;                  //MOVE stored in D_pos
			            }

			        
			        if(D<=0 || D>9 || D==arr[AI[i]] || D==arr[AI[j]]|| i==j) //boxes aren't collinear
			        {
                                                      //Do nothing

			        }
			        else                              //boxes are collinear
			        {
			            int temp=0;
		                for(int l=1;l<a;l++)          //checking if AI has already played the turn
		                {
			            if(AI[l]==D_pos)
			            temp++;
		                }
                                for(int l=1;l<p;l++) //checking if Opponent has played the turn
		                {
			            if(Player[l]==D_pos)
			            temp++;
		                }
 
		                if(temp==0)                  //AI has turn to win
		                {
                                  
                                    return D_pos;
		                }
                                
		                
		            
			        }
			    }
			}
                       
                       
                            return 0;
                        
                
		}
		else if (player==2)//check if human opponent can win
		{
			for(int i=1; i<p; i++)
			{
			    for(int j=1; j<p; j++)
			    {
			        if(i!=j)
			        {
                        /*
                        
                        CALCULATES MAGIC_SQUARE_VALUE OF MOVE TO PLAY TO WIN= 15-(SUM OF 2 CO-LINEAR MOVES ON BOARD)
                        SINCE ALL ROWS, COLUMNS AND DIAGONALS SUM UP TO 15 FOR 3*3 MAGIC SQUARE 
                        
                        */
                        
			            D=15-(arr[Player[i]]+arr[Player[j]]);

			            for(int k=1; k<=9; k++)        //LOOKING FOR MOVE VALUE CORRESPONDING TO THE MAGIC_SQUARE_VALUE CALCULATED, D
			            {
			                if(arr[k]==D)
			                D_pos=k;                   //MOVE stored in D_pos
			            }

			        }
			        if(D<=0 || D>9 || D==arr[Player[i]] || D==arr[Player[j]]||i==j) //boxes aren't collinear
			        {
                                                       //Do nothing
			        }
			        else                               //boxes are collinear
			        {
			            int temp=0;
		                for(int l=1;l<a;l++)           //checking if AI has already played the turn
		                {
			            if(AI[l]==D_pos)
			            temp++;
		                }
                                for(int l=1;l<p;l++) //checking if Opponent has played the turn
		                {
			            if(Player[l]==D_pos)
			            temp++;
		                }
 
		                if(temp==0)                  //Opponent has turn to win
		                {
                                  
                                    return D_pos;
		                }
		                
		            
			        }
			    }
			}
		}
                
                return 0;
	}
}

