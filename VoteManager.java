import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VoteManager {
    private Map<Candidate, Integer> candidateToVotesCount;
    private Set<String> votedUsers;

    public VoteManager() {
        this.candidateToVotesCount = new HashMap<>();
        this.votedUsers = new HashSet<>();
    }

    public void vote(Candidate candidate, String username) {
        if (votedUsers.contains(username)) {
            throw new IllegalArgumentException("You can only vote once");
        } else {
            Integer currentVoteCount = candidateToVotesCount.getOrDefault(candidate, 0);
            candidateToVotesCount.put(candidate, currentVoteCount + 1);
            votedUsers.add(username);
        }
    }

    public Map<Candidate, Integer> getResults() {
        return candidateToVotesCount;
    }
}
