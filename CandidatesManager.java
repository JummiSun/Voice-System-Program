import java.util.HashSet;
import java.util.Set;

public class CandidatesManager {
    private final Set<Candidate> candidates;

    public CandidatesManager() {
        this.candidates = new HashSet<>();
    }

    public void add(String firstname, String lastname, int partiNumber) {
        candidates.add(new Candidate(firstname, lastname, partiNumber));
    }

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public Candidate getCandidateByPartiNumber(int partiNumber) {
        for (Candidate candidate : candidates) {
            if (candidate.getPartiNumber() == partiNumber) {
                return candidate;
            }
        }
        return null;
    }
}