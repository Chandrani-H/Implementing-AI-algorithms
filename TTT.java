

/**
 *
 * @author chandrani96
 */
/* package whatever; // don't place package name! */

package ttt;

import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class TTT_magic
{
	static int arr[];
	static char brr[];
   	static int Player[];
	static int AI[];
	static int p;
	static int a;

	public static void main (String[] args) throws java.lang.Exception
	{
			p=1;
			a=1;
			arr=new int[10];
			brr=new char[10];
			arr[1]=4; arr[2]=9; arr[3]=2;
   			arr[4]=3; arr[5]=5; arr[6]=7;
   			arr[7]=8; arr[8]=1; arr[9]=6;

   			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   			Player=new int[10];
   			AI=new int[10];
   			
   			for(int i=0;i<10;i++)
			{
				Player[i]=0;
				AI[i]=0;
                                brr[i]='-';
			}
			
   			System.out.println("CHOOSE :");
			System.out.println("1. Computer goes First");
			System.out.println("2. Human goes First");
			int choice=Integer.parseInt(br.readLine()); //input choice
			
			if(choice==1) //AI plays first
			{
				go(1,1);
                                int b=1;
                                user_input();
                                System.out.println("My turn !");
                                if(brr[9]=='-')
                                {go(9,1);}
                                else if(make_2()!=0)
                                { int x=  make_2();
                                  go(x,1);
                                }
				while(true)
                                {
                                    
                                    if(b==1)
                                    {
                                        user_input();
                                        b=b*-1;
                                    }
                                    else
                                    {
                                        System.out.println("My turn !");
                                        if(poss_win(1)!=0)
                                        {
                                            int x=poss_win(1);
                                            go(x,1);
                                            System.out.println("haha I win !");
                                            System.exit(0);
                                        }
                                        else if(poss_win(2)!=0)
                                        {
                                            int x=poss_win(2);
                                            go(x,1);
                                            System.out.println("Gotcha !");
                                        }
                                        else if(make_2()!=0)
                                        {
                                            int x=  make_2();
                                            go(x,1);
                                        }
                                        else
                                        {
                                            System.out.println("It's going to be a draw !");
                                        }
                                        
                                     b=b*-1;
                                
                                }
                            }
                                
			}
			else //AI plays second
			{
                            user_input();
                            System.out.println("My turn !");
                            if(make_2()!=0)
                                { int x=  make_2();
                                  go(x,1);
                                }
                            int b=1;
                                
				while(true)
                                {
                                    
                                    if(b==1)
                                    {
                                        user_input();
                                        b=b*-1;
                                    }
                                    else
                                    {
                                        System.out.println("My turn !");
                                        if(poss_win(1)!=0)
                                        {
                                            int x=poss_win(1);
                                            go(x,1);
                                            System.out.println("haha I win !");
                                                    break;
                                        }
                                        else if(poss_win(2)!=0)
                                        {
                                            int x=poss_win(2);
                                            go(x,1);
                                            System.out.println("Gotcha !");
                                        }
                                        else if(make_2()!=0)
                                        {
                                            int x=  make_2();
                                            go(x,1);
                                        }
                                        else
                                        {
                                            System.out.println("It's going to be a draw !");
                                        }
                                        
                                     b=b*-1;
                                
                                }
                                }
				
			}
			
			
			//int c=make_2();

			//for(int i=0;i<10;i++)
			//{
			//	System.out.println(Player[i]);
			//}
			
			
	}
    static void user_input()throws IOException
    {
        BufferedReader sr=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your turn !");
        int user_inp=Integer.parseInt(sr.readLine()); 
        go(user_inp,2);
    }
    static void display()
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
        
        //prints arrays
        
        /*System.out.println("AI played");
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
    
	static void go(int num, int player) //1 for Computer, 2 for player //num=position of 
	{
		if(player==1) //add to Computer's list of moves
		{
			AI[a]=num;
			a++;
			
		}
		else  //add to Player's list of moves
		{
			Player[p]=num;
			p++;
		}
                display();
	}
	
	static int make_2()
	{
		int c=0;
		for(int i=1;i<10;i++)
		{
			if(Player[i]==5 || AI[i]==5)
			c++;
		}

		if(c==0)
		{
			return 5;
		}
		else
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
	
	static int poss_win(int player)  //1 for Computer, 2 for player //return pos to play to win else 0
	{
	    int D=0;
	    int D_pos=0;
		if(player==1) //check if computer can win
		{
			for(int i=1; i<a; i++)
			{
			    for(int j=1; j<a; j++)
			    {
			        
			            D=15-(arr[AI[i]]+arr[AI[j]]);
                                    //System.out.println("D : "+D+" "+arr[AI[i]]+" "+arr[AI[j]]);
                                    //System.out.println(AI[i]+" "+AI[j]);
			            for(int k=1; k<=9; k++) //loking for which move to play
			            {
			                if(arr[k]==D)
			                D_pos=k;
			            }
                                    //System.out.println("D_posA : "+D_pos); //prints move
			        
			        if(D<=0 || D>9 || D==arr[AI[i]] || D==arr[AI[j]]|| i==j) //boxes aren't collinear
			        {
                                        //System.out.println( "Turns out: "+ arr[AI[i]] + " and " + arr[AI[j]] + " aren't collinear !" );

			        }
			        else  //boxes are collinear
			        {
			            int temp=0;
		                for(int l=1;l<a;l++) //checking if Ai has already played the turn
		                {
			            if(AI[l]==D_pos)
			            temp++;
		                }
                                for(int l=1;l<p;l++) //checking if player has played the turn
		                {
			            if(Player[l]==D_pos)
			            temp++;
		                }
 
		                if(temp==0) //AI has turn to win
		                {
                                  
                                    return D_pos;
		                }
                                
		                
		            
			        }
			    }
			}
                       
                       
                            return 0;
                        
                
		}
		else if (player==2)//check if human can win
		{
			for(int i=1; i<p; i++)
			{
			    for(int j=1; j<p; j++)
			    {
			        if(i!=j)
			        {
			            D=15-(arr[Player[i]]+arr[Player[j]]);
                                    //System.out.println("D : "+D);
			            for(int k=1; k<=9; k++)
			            {
			                if(arr[k]==D)
			                D_pos=k;
			            }
                                    //System.out.println("D_posP : "+D_pos+" "+arr[Player[i]]+" "+arr[Player[j]]);
			        }
			        if(D<=0 || D>9 || D==arr[Player[i]] || D==arr[Player[j]]||i==j) //boxes aren't collinear
			        {
			            
			        }
			        else  //boxes are collinear
			        {
			            int temp=0;
		                for(int l=1; l<p; l++)
		                {
			            if(Player[l]==D_pos || AI[l]==D_pos)
			            temp++;
		                }

		                if(temp==0)
		                {
			            //System.out.println("Returns to print"+ D_pos);
                                    //System.exit(0);
                                    return D_pos;
		                }
		                
		            
			        }
			    }
			}
		}
                
                return 0;
	}
}

