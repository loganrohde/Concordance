# hello-world
IDK what is going on

Hello world, my name is Logan. I am making edits to this git.


public static void main(String[] args) throws FileNotFoundException {
        
        String filenm = "test.txt";
        Scanner in = new Scanner(new File(filenm));
        FileReader fr = new FileReader(filenm);
 
        /*while(in.hasNext()){
            String nextString = in.next().toLowerCase();
            System.out.println(nextString);
        }*/
        Concord concordance = new Concord();
        concordance.start(filenm);
    }
