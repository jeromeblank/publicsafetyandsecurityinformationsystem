package cnts21s4;

import java.util.ArrayList;
import java.util.List;

public class CrimeReportSystem {
    private List<Crime> crimeList = new ArrayList<>();

    public void addCrime(Crime crime) {
        crimeList.add(crime);
    }

    public List<Crime> getCrimeList() {
        return crimeList;
    }
}
