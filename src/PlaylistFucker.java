import java.util.Scanner;

public class PlaylistFucker {
	public static void main(String[] args) {
		FileHandeler music = new FileHandeler();
		FileHandeler spot = new FileHandeler();
		Scanner scan = new Scanner(System.in);
		String outFile = "";
		int tracks, spotTrackCounter = 0;

		System.out.println("Velg m3u filen som inneholder musikken:");
		music.openFile(); // Gir brukeren en dialogboks til Œ velge fil
		System.out.println("Velg m3u filen som inneholder spotene:");
		spot.openFile(); // Gir brukeren en dialogboks til Œ velge fil
		System.out.println("Hvor mange sanger mellom hv¾r spot? ");
		tracks = scan.nextInt();

		int musicCount = music.getFile().size();
		int spotCount = spot.getFile().size();

		// Count the number of music tracks
		System.out.println("musikk: " + musicCount + "\nspots: " + spotCount);

		music.shuffle();

		for (int i = 0; i < music.getFile().size(); i++) {
			if (i % tracks == 0 && i != 0) {
				outFile += spot.getLine(spotTrackCounter++) + "\r\n";
				if (spotTrackCounter >= spotCount)
					spotTrackCounter = 0;
			}

			outFile += music.getLine(i) + "\r\n";
		}

		music.saveFile(outFile);
		System.exit(0);
	}
}