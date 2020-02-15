import org.fusesource.jansi.AnsiConsole;
import java.io.*;
import java.util.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class castle
{
	public static final String CLS = "\u001b[2J\u001b[1;1H";
	public static final String RED = "\u001b[31;1m";
	public static final String GREEN = "\u001b[32;1m";
	public static final String YELLOW = "\u001b[33;1m";
	public static final String BLUE = "\u001b[34;1m";
	public static final String PURPLE = "\u001b[35;1m";
	public static final String CYAN = "\u001b[36;1m";
	public static final String WHITE = "\u001b[37;1m";
	public static final String NORMAL = "\u001b[0m"; // default gray color
	public static final String WHITEONBLUE = "\u001b[37;44m";
	public static final String BLUEONWHITE = "\u001b[34;47m";

 	static ArrayList<Room> Vertex = new ArrayList<Room>();
	static ArrayList<Room> Path = new ArrayList<Room>();

	public static void main(String[] args) throws IOException, InterruptedException,FileNotFoundException, NoSuchElementException
	{
		AnsiConsole.systemInstall();
	    Scanner out = new Scanner(System.in);

		Audio audio = new Audio();

		audio.playSound("got.wav");



	System.out.println(RED+"	 __    __     _                            _          _   _                                       ");
	System.out.println("	/ / /\\ \\ \\___| | ___ ___  _ __ ___   ___  | |_ ___   | |_| |__   ___    __ _  __ _ _ __ ___   ___ ");
	System.out.println("	\\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __| '_ \\ / _ \\  / _` |/ _` | '_ ` _ \\ / _ \\ ");
	System.out.println("	 \\ \\  /\\  /  __/ | |_| (_) | | | | | |  __/ | || (_) | | |_| | | |  __/ | (_| | (_| | | | | | |  __/");
	System.out.println("	 \\ \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__|_| |_|\\___|  \\__, |\\__,_|_| |_| |_|\\___|");
	System.out.println("                                                                              |___/                      "+RED);




		System.out.print(GREEN+"Please enter your name: "+NORMAL);
		String name = out.nextLine();

		System.out.println(CLS);
		dragon();
		System.out.println(CYAN+"Welcome aboard "+NORMAL+GREEN+name+NORMAL +CYAN+" , lets begin the journey "+NORMAL);
		System.out.println(PURPLE+"Press Enter to continue..."+NORMAL);
		new java.util.	Scanner(System.in).nextLine();
		System.out.print(CLS);
		//String Choice = "";



		//music(got.mp3);


		System.out.println(PURPLE+"\t\t\t\t\t\t\tThis is the map."+NORMAL);
		map();
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner fb = new Scanner(System.in);

		// read in vertices
		File file = new File("vertex.txt");
		Scanner infile = new Scanner(file);
		String Input = "";
		while (infile.hasNextLine())
		{
			Input = infile.nextLine();
			Vertex.add(new Room(Input));
		}
		//System.out.println(Vertex.size() + " vertices read from file");

		// read in edges
		file = new File("edge.txt");
		infile = new Scanner(file);
		String From, Direction, To;
		int Count=0;
		while (infile.hasNext())
		{
			Count++;
			From = infile.next();
			Direction = infile.next();
			To = infile.next();
			// locate From Vertex in ArrayList
			int IndexFrom = 0;
			while (!Vertex.get(IndexFrom).RoomName.equals(From))
			{  IndexFrom++;  }
			// locate To Vertex in ArrayList
			int IndexTo = 0;
			while (!Vertex.get(IndexTo).RoomName.equals(To))
			{  IndexTo++;  }
			// create edge
			if (Direction.equals("North"))
			{
				Vertex.get(IndexFrom).North = Vertex.get(IndexTo);
				Vertex.get(IndexTo).South = Vertex.get(IndexFrom);
			}
			if (Direction.equals("South"))
			{
				Vertex.get(IndexFrom).South = Vertex.get(IndexTo);
				Vertex.get(IndexTo).North = Vertex.get(IndexFrom);
			}
			if (Direction.equals("East"))
			{
				Vertex.get(IndexFrom).East = Vertex.get(IndexTo);
				Vertex.get(IndexTo).West = Vertex.get(IndexFrom);
			}
			if (Direction.equals("West"))
			{
				Vertex.get(IndexFrom).West = Vertex.get(IndexTo);
				Vertex.get(IndexTo).East = Vertex.get(IndexFrom);
			}
			if (Direction.equals("NWest"))
			{
				Vertex.get(IndexFrom).NWest = Vertex.get(IndexTo);
				Vertex.get(IndexTo).SEast = Vertex.get(IndexFrom);
			}
			if (Direction.equals("SWest"))
			{
				Vertex.get(IndexFrom).SWest = Vertex.get(IndexTo);
				Vertex.get(IndexTo).NEast = Vertex.get(IndexFrom);
			}
			if (Direction.equals("NEast"))
			{
				Vertex.get(IndexFrom).NEast = Vertex.get(IndexTo);
				Vertex.get(IndexTo).SWest = Vertex.get(IndexFrom);
			}
			if (Direction.equals("SEast"))
			{
				Vertex.get(IndexFrom).SEast = Vertex.get(IndexTo);
				Vertex.get(IndexTo).NWest = Vertex.get(IndexFrom);
			}
		}
				//System.out.println(Count + " edges read from file");
				//System.out.println("Press <Enter> to Continue");
				//String Choice = in.nextLine();


				Room Temp = Vertex.get(0); //using temp to locate where you are currently at.
				int q = 0 ;
				int i = 0;



			   String Choice = " ";
			   int Points = 0;
			   int Lives = 3;

		while(!Choice.equals("q")||!Choice.equals("Q"))
		{
			//System.out.println(PURPLE+"\n\t\t\tGame Objective: Traverse through different rooms and answer questions to earn points."+NORMAL);

			System.out.println(YELLOW+"COMMANDS: "+RED+"N"+NORMAL+CYAN+"ORTH "+NORMAL+RED+"S"+NORMAL+CYAN+"OUTH"+NORMAL+RED+" E"+NORMAL+CYAN+"AST"+NORMAL+RED+" W"+NORMAL+CYAN+"EST "+NORMAL+RED+" SE"+NORMAL+CYAN+"ast"+NORMAL+RED+"  SW"+NORMAL+CYAN+"est"+RED+"  NE"+NORMAL+CYAN+"ast"+NORMAL+RED+"  NW"+NORMAL+CYAN+"est"+RED+ "\n \t  F"+NORMAL+CYAN+"ind Path"+NORMAL+RED+" M"+NORMAL+CYAN+"AP"+NORMAL);
			System.out.println(YELLOW+"You are in room "+NORMAL+GREEN+Temp.RoomName+NORMAL);
			System.out.print(YELLOW+"Your Command: "+NORMAL);
			String Choice2 = in2.nextLine();



				if(Choice2.equals("n") && Temp.North != null)
					Temp = Temp.North;
				else if(Choice2.equals("n") && Temp.North == null)
					System.out.println(CYAN+"Cant go there buddy!!"+NORMAL);
				if(Choice2.equals("s") && Temp.South!=null)
					Temp = Temp.South;
				else if(Choice2.equals("s") && Temp.South == null)
					System.out.println(CYAN+"Cant go there buddy!!"+NORMAL);
				if(Choice2.equals("e") && Temp.East!=null)
					Temp = Temp.East;
				else if(Choice2.equals("e") && Temp.East == null)
					System.out.println(CYAN+"Cant go there buddy!!"+CYAN);
				if(Choice2.equals("w") && Temp.West!=null)
					Temp = Temp.West;
				else if(Choice2.equals("w") && Temp.West == null)
					System.out.println(CYAN+"Cant go there buddy!!"+CYAN);
				if(Choice2.equals("nw") && Temp.NWest!=null)
					Temp = Temp.NWest;
				else if(Choice2.equals("nw") && Temp.NWest == null)
					System.out.println("Cant go there buddy!!");
				if(Choice2.equals("sw") && Temp.SWest!=null)
					Temp = Temp.SWest;
				else if(Choice2.equals("sw") && Temp.SWest == null)
					System.out.println("Cant go there buddy!!");
				if(Choice2.equals("ne") && Temp.NEast!=null)
					Temp = Temp.NEast;
				else if(Choice2.equals("ne") && Temp.NEast == null)
					System.out.println("Cant go there buddy!!");
				if(Choice2.equals("se") && Temp.SEast!=null)
					Temp = Temp.SEast;
				else if(Choice2.equals("se") && Temp.SEast == null)
					System.out.println("Cant go there buddy!!");


				if(Choice2.equals("f"))
				{
					System.out.println(CYAN+"Which room would you like to go? "+NORMAL);
					String Choice1 = in1.nextLine();
					while(!Vertex.get(i).RoomName.equals(Choice1))
					{
						i++;
					}
					Room d1 = Vertex.get(i);
					Dijkstra(Temp, d1);
					System.out.println(RED+"Shortest Path: "+NORMAL+CYAN+(Path.size()- 2)+NORMAL);
					for(int k = 1 ; k < Path.size(); k++)
					{
						System.out.println(CYAN+Path.get(k).RoomName+ " "+NORMAL);
					}
				}

				if(Choice2.equals("m")||Choice2.equals("M"))
				{
					map();
				}

				/*if(Choice2.equals("a")||Choice2.equals("A"))
				{
					System.out.println("Adjancency List for "+Temp.RoomName);
					for(int c = 0 ; c < Path.size(); c++)
					{
						System.out.println(Path.get(c).RoomName+" ");
					}


				}
				*/
			}
		}


			//	System.out.println(Vertex.get(0).RoomName);
			//	System.out.println(Temp.South.East.RoomName);
				//System.out.println(Temp.South.West.RoomName);


				static void dragon()
				{

					System.out.println(GREEN+"	                                        ,   ,                                         ");
					System.out.println("	                                        $,  $,     ,									");
					System.out.println("	                                        ss.$ss. .s'                                     ");
					System.out.println("	                                ,     .ss$$$$$$$$$$s,									");
					System.out.println("	                                $. s$$$$$$$$$$$$$$`$$Ss                            ");
					System.out.println("	                                $$$$$$$$$$$$$$$$$$o$$$       ,               ");
					System.out.println("	                               s$$$$$$$$$$$$$$$$$$$$$$$$s,  ,s");
					System.out.println("	                              s$$$$$$$$$$$$$$$$$$$$$$$$$$$$,");
					System.out.println("	                              s$$$$$$$$$$s$$$$ssssss$$$$$$$$$$     ");
					System.out.println("	                             s$$$$$$$$$$'         `'''ss$s    ");
					System.out.println("	                             s$$$$$$$$$$,              `$  .s$$s  ");
					System.out.println("	                             s$$$$$$$$$$$$s,...               `s$$'  ");
					System.out.println("	                         `ssss$$$$$$$$$$$$$$$$$$$$####s.     .$$$.   , s-");
					System.out.println("	                           `$$$$$$$$$$$$$$$$$$$$$$$$#####$$$$$$      ");
					System.out.println("	                                  $$$$$$$$$$$$$$$$$$$$$####s     .$$$|");
					System.out.println("	                                  $$$$$$$$$$$$$$$$$$$$$$$$##s    .$$" );
					System.out.println("	                                   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   `");
					System.out.println("	                                     $$$$$$$$$$$$$$$$$$$$S");
					System.out.println("	                             ,   ,       $$$$$$$$$$$$$$$$####s");
					System.out.println("	                             $.          .s$$$$$$$$$$$$$$$$$####");
					System.out.println("	                 ,           $s.   ..ssS$$$$$$$$$$$$$$$$$$$####");
					System.out.println("	                 $           .$$$S$$$$$$$$$$$$$$$$$$$$$$$$#####");
					System.out.println("	                 Ss     ..sS$$$$$$$$$$$$$$$$$$$$$$$$$$$######");
					System.out.println("	                  $$sS$$$$$$$$$$$$$$$$$$$$$$$$$$$########");
					System.out.println("	           ,      s$$$$$$$$$$$$$$$$$$$$$$$$#########");
					System.out.println("	           $    s$$$$$$$$$$$$$$$$$$$$$#######'      s'         ,");
					System.out.println("	           $$..$$$$$$$$$$$$$$$$$$######       ....,$$....    ,$");
					System.out.println("	            $$$$$$$$$$$$$$$###### ,     .sS$$$$$$$$$$$$$$$$s$$");
					System.out.println("	              $$$$$$$$$$$$#####     $, .s$$$$$$$$$$$$$$$$$$$$$$$$s.");
					System.out.println("	   )          $$$$$$$$$$$#####'      `$$$$$$$$$###########$$$$$$$$$$$.");
					System.out.println("	  ((          $$$$$$$$$$$#####       $$$$$$$$###       ####$$$$$$$$$$");
					System.out.println("	  ) \\         $$$$$$$$$$$$####.     $$$$$$###             ###$$$$$$$$$   s'");
					System.out.println("	 (   )        $$$$$$$$$$$$$####.   $$$$$###                ####$$$$$$$$s$$'");
					System.out.println("	 )  ( (       $$$$$$$$$$$$$#####.$$$$$###'                .###$$$$$$$$$$");
					System.out.println("	 (  )  )   _,$   $$$$$$$$$$$$######.$$##'                .###$$$$$$$$$$");
					System.out.println("	 ) (  ( \\.         $$$$$$$$$$$$$#######,,,.          ..####$$$$$$$$$$$");
					System.out.println("	(   )$ )  )        ,$$$$$$$$$$$$$$$$$$####################$$$$$$$$$$$");
					System.out.println("	(   ($$  ( \\     _sS  `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$S$$,");
					System.out.println("	 )  )$$$s ) )  .      .   `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$'  `$$");
					System.out.println("	  (   $$$Ss/  .$,    .$,,s$$$$$$##S$$$$$$$$$$$$$$$$$$$$$$$$S        '");
					System.out.println("		_$$$$$$$$$$$$$$$$$$$$$$$##  $$        `$$.        `$$.");
					System.out.println("	        `S$$$$$$$$$$$$$$$$$#      $          `$          `$");
					System.out.println("	           $$$$$$$$$$$$$$$$'         '           '           '");
					System.out.println("	------------------------------------------------"+GREEN);
				}


			/*	static void did()
				{
					System.out.println(CLS);
					System.out.println(PURPLE+"	 _______   __   _______     ____    ____  ______    __    __      __  ___ .__   __.   ______   ____    __    ____  ______        ");
					System.out.println("	|       \\ |  | |       \\    \\   \\  /   / /  __  \\  |  |  |  |    |  |/  / |  \\ |  |  /  __  \\  \\   \\  /  \\  /   / |    ");
					System.out.println("	|  .--.  ||  | |  .--.  |    \\   \\/   / |  |  |  | |  |  |  |    |  '  /  |   \\|  | |  |  |  |  \\   \\/    \\/   /  `----  ");
					System.out.println("	|  |  |  ||  | |  |  |  |     \\_    _/  |  |  |  | |  |  |  |    |    <   |  . `  | |  |  |  |   \\            /       /  /  ");
					System.out.println("	|  '--'  ||  | |  '--'  |       |  |    |  `--'  | |  `--'  |    |  .  \\  |  |\\   | |  `--'  |    \\    /\\    /       |__|   ");
					System.out.println("	|_______/ |__| |_______/        |__|     \\______/   \\______/     |__|\\__\\ |__| \\__|  \\______/      \\__/  \\__/         __"+NORMAL);

				}
		*/


				/*public static void music(String filepath)
				{
					InputStream music;
					try
					{
						music = new FileInputStream(new File(filepath));
						AudioStream got= new AudioStream(music);
						AudioPlayer.player.start(got);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,"Error");
					}
				}
				*/





				static void map()
				{

				  System.out.println(RED+"      ___________                                                                                                       __________      ");
				  System.out.println("     |           |         _______                                                              __________             |          |     ");
				  System.out.println("     |Harrenhal  |        |       |             ______     __________     _______              |          |            |Highgarden|     ");
				  System.out.println("     |           |________|  The_ |____________| Bear_|___| Castle_  |___| The_  |_____________| Dothraki |____________|          |     ");
				  System.out.println("     |            ________  Arbor  ____________ Island ___  Black     ___ Citadel _____________    Sea     ____________           |     ");
				  System.out.println("     |____   ____|        |       |            |____  |   |___    ___|   |  _____|             |          |            |___   ____|     ");
				  System.out.println("          | |             |_______|                 \\ \\       |  |       / /                   |__________|                | |          ");
				  System.out.println("          | |                                        \\ \\      |  |      / /                                                | |          ");
				  System.out.println("          | |                                         \\ \\     |  |     / /                                                 | |          ");
				  System.out.println("     _____| |__________          ________              \\ \\    |  |    / /             ________                    _________| |_______   ");
				  System.out.println("    |                  |        |        |              \\ \\___|  |___/ /             |        |                  |                   |  ");
				  System.out.println("    |  Casterly_Rock   |        |Valyria |_______________\\  King's     /_____________|Saltrock|                  |      Iron_Island  |  ");
				  System.out.println("    |                  |        |         ________________  Landing    ______________         |                  |                   |  ");
				  System.out.println("    |_____   __________|        |________|                / __    ___ \\              |________|                  |_________  ________|  ");
				  System.out.println("          | |                                            / /  |  |   \\ \\                                                   | |          ");
				  System.out.println("          | |                                           / /   |  |    \\ \\                                                  | |          ");
				  System.out.println("          | |                                          / /    |  |     \\ \\                                                 | |          ");
				  System.out.println("          | |                                  _______/ /     |  |      \\ \\________                                        | |          ");
				  System.out.println("          | |                                 |        /      |  |       \\         |                                       | |          ");
				  System.out.println("          | |                                 |Tarth  |       |  |        |  Sept  |                                       | |          ");
				  System.out.println("          | |                                 |_______|       |  |        |________|                                       | |          ");
				  System.out.println("          | |                                                 |  |                                                         | |          ");
				  System.out.println("       ___| |_           _________                            |  |                              __________               __| |___       ");
				  System.out.println("      |       |         |         |                       ____|  |____                         |          |             |        |      ");
				  System.out.println("      |Dorne  |_________| Braavos |______________________|            |________________________|          |_____________|        |      ");
				  System.out.println("      |        _________           ______________________    Starfall  ________________________  Volantis  _____________ The_Wall|      ");
				  System.out.println("      |       |         |         |                      |            |                        |          |             |        |      ");
    		      System.out.println("      |_______|         |_________|                      |____________|                        |__________|             |________|      "+NORMAL);

			}




		static void Dijkstra(Room Start, Room Finish)
		{
			// set distance to all rooms (except for Start) to 1000 and visited = false
			for (int i=0; i<Vertex.size(); i++)
			{
				if (Vertex.get(i) == Start)
					Vertex.get(i).Distance = 0;
				else
					Vertex.get(i).Distance = 1000;  // set distance to "infinity"
				Vertex.get(i).Visited = false;
			}
			// Do Dijkstra - find Distance to each room
			Room Temp = Start;
			while (!Finish.Visited)
			{
				Temp.Visited = true;
				if (Temp.North!=null && !Temp.North.Visited && Temp.North.Distance > Temp.Distance+1)
					Temp.North.Distance = 1 + Temp.Distance;
				if (Temp.NEast!=null && !Temp.NEast.Visited && Temp.NEast.Distance > Temp.Distance+1)
					Temp.NEast.Distance = 1 + Temp.Distance;
				if (Temp.NWest!=null && !Temp.NWest.Visited && Temp.NWest.Distance > Temp.Distance+1)
					Temp.NWest.Distance = 1 + Temp.Distance;
				if (Temp.South!=null && !Temp.South.Visited && Temp.South.Distance > Temp.Distance+1)
					Temp.South.Distance = 1 + Temp.Distance;
				if (Temp.SWest!=null && !Temp.SWest.Visited && Temp.SWest.Distance > Temp.Distance+1)
					Temp.SWest.Distance = 1 + Temp.Distance;
				if (Temp.SEast!=null && !Temp.SEast.Visited && Temp.SEast.Distance > Temp.Distance+1)
					Temp.SEast.Distance = 1 + Temp.Distance;
				if (Temp.East!=null && !Temp.East.Visited && Temp.East.Distance > Temp.Distance+1)
					Temp.East.Distance = 1 + Temp.Distance;
				if (Temp.West!=null && !Temp.West.Visited && Temp.West.Distance > Temp.Distance+1)
					Temp.West.Distance = 1 + Temp.Distance;


				int Smallest = 1000;
				int SmallestIndex = 0;
				for (int i=0; i<Vertex.size(); i++)
				{
					if (!Vertex.get(i).Visited && Vertex.get(i).Distance < Smallest)
					{
						Smallest = Vertex.get(i).Distance;
						SmallestIndex = i;
					}
				}
				Temp = Vertex.get(SmallestIndex);
			}

			// populate Path ArrayList with Rooms of shortest path
			Temp = Finish;
			Path.clear();
			Path.add(0,Finish);
			while (Temp != Start)
			{
				int N = 1000, S = 1000, E = 1000, W = 1000, SW = 1000 , SE = 1000, NW = 1000 , NE = 1000;
				if (Temp.North != null)  N = Temp.North.Distance;
				if (Temp.South != null)  S = Temp.South.Distance;
				if (Temp.East != null)  E = Temp.East.Distance;
				if (Temp.West != null)  W = Temp.West.Distance;
				if (Temp.SWest != null) SW = Temp.SWest.Distance;
				if (Temp.SEast != null) SE = Temp.SEast.Distance;
				if (Temp.NWest != null) NW = Temp.NWest.Distance;
				if (Temp.NEast != null) NE = Temp.NEast.Distance;
				if (N < S && N < E && N < W && N < SW && N < SE && N < NW && N < NE)
					Temp = Temp.North;
				else if (S < E && S < W && S < SW && S < SE && S < NW && S < NE)
					Temp = Temp.South;
				else if (E < W && E < SW && E < SE && E < NW && E < NE)
					Temp = Temp.East;
				else if (W < SW && W < SE && W < NW && W < NE)
					Temp = Temp.West;
				else if (SW < SE && SW <NW && SW < NE)
					Temp = Temp.SWest;
				else if (SE < NW && SE < NE)
					Temp = Temp.SEast;
				else if (NW < NE)
					Temp = Temp.NWest;
				else
					Temp = Temp.NEast;
				Path.add(0,Temp);
			}
			//System.out.println(Temp.East.RoomName);

		}
}
class Room
{
    String RoomName;
    Room North, South, East, West , NWest, SWest , NEast , SEast;
    boolean Visited;   // used for Dijkstra
    int Distance;      // used for Dijkstra

    Room (String theRoomName)
    {  RoomName = theRoomName;  }
}

 class Audio{
	public static void playSound(String songname)
	{
		try
		{
			AudioInputStream audioInputStream =
				AudioSystem.getAudioInputStream(new File(songname).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
    		}
	}
}
