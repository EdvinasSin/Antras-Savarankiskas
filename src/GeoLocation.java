import java.util.Random;

public class GeoLocation {

    private final double lat;
    private final double lon;
    private static int numLocations = 0;
    Random random = new Random();

    public GeoLocation() {

        this.lat = Math.round((random.nextDouble() * 180 - 90) * 1_000_000) / 1_000_000.0;
        this.lon = Math.round((random.nextDouble() * 180 - 90) * 1_000_000) / 1_000_000.0;
        numLocations++;
    }

    public GeoLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        numLocations++;
    }

    public GeoLocation(GeoLocation location) {
        this.lat = Math.round((location.lat + (random.nextDouble() * 0.1 - 0.1)) * 1_000_000) / 1_000_000.0;
        this.lon = Math.round((location.lon + (random.nextDouble() * 0.1 - 0.1)) * 1_000_000) / 1_000_000.0;
        numLocations++;
    }


    void print() {
        System.out.println("[" + lat + ", " + lon + "]");
    }

    static int  getNumLocations() {
        return numLocations;
    }

    public static double distance(GeoLocation loc1, GeoLocation loc2) {
        final int R = 6371;
        double latDistance = Math.toRadians(loc2.lat - loc1.lat);
        double lonDistance = Math.toRadians(loc2.lon - loc1.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.lat)) * Math.cos(Math.toRadians(loc2.lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return Math.round(distance * 10) / 10.0;
    }
}
