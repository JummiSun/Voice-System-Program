import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Voice {
    private static UserManager userManager = new UserManager();
    private static CandidatesManager candidatesManager = new CandidatesManager();

    private static VoteManager voteManager = new VoteManager();



    public static void main(String[] args) {
        mainMenu();

    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("---- Main Menu ----\n1) User actions\n2) Admin actions\n3) Show results\nSelect: ");
        int role = sc.nextInt();

        while (true) {
            if (role == User.USER_ROLE) {
                System.out.println("----- User Commands ----");
//                System.out.print("1) Register\n2) Login\n3) Logout\n4) Current user\n6) Vote\n7) Back\nSelect: ");
                System.out.print("1) Register\n2) Login\n3) Logout\n4) Current user\n5) Vote\n6) Back\nSelect: ");
                int command = sc.nextInt();
                handleUserCommand(command);
            } else if (role == User.ADMIN_ROLE) {
                System.out.println("----- Admin Commands ----");
                System.out.print("1) Login\n2) Show candidates\n3) Add candidate\n4) Back\nSelect: ");
                int command = sc.nextInt();
                handleAdminCommand(command);
            } else if (role == User.SHOW_RESULTS) {
                handleShowResults();
            }
        }
    }

    private static void handleShowResults() {
        Map<Candidate, Integer> results = voteManager.getResults();
        if (results.isEmpty()) {
            System.out.println("There are no results of voting");
        } else {
            System.out.println("Voting results:");
            for (Map.Entry<Candidate, Integer> entry : results.entrySet()) {
                System.out.println(entry.getKey().getPartiNumber() + ") " + entry.getKey().getFullname() + " got " + entry.getValue() + " vote(s)");
            }
        }
        mainMenu();
    }

    private static void handleUserCommand(int command) {
        //                System.out.print("1) Register\n2) Login\n3) Logout\n4) Current user\n5) Vote\n6) Back\nSelect: ");

        if (command == UserCommand.REGISTER) {
            handleUserRegisterCommand();
        } else if (command == UserCommand.LOGIN) {
            handleUserLoginCommand();
        } else if (command == UserCommand.LOGOUT) {
            handleUserLogoutCommand();
        } else if (command == UserCommand.BACK) {
            mainMenu();
        } else if (command == UserCommand.SHOW_CURRENT_USER) {
            handleUserShowCurrentUserCommand();
        } else if (command == UserCommand.VOTE) {
            if (!userManager.isUserLoggedIn()) {
                System.out.println("User should be logged in to vote for candidates");
            } else {
                handleUserVoteCommand();
            }
        }
    }

    private static void handleUserVoteCommand() {
        Set<Candidate> candidates = candidatesManager.getCandidates();
        if (candidates.isEmpty()) {
            System.out.println("There are no candidates");
        } else {
            System.out.println("Registered candidates: ");
            for (Candidate candidate : candidates) {
                System.out.println(candidate.getPartiNumber() + ") " + candidate.getFullname());
            }
            System.out.print("Vote for candidate with parti number: ");
            int partiNumber = new Scanner(System.in).nextInt();
            Candidate candidate = candidatesManager.getCandidateByPartiNumber(partiNumber);
            if (candidate == null) {
                System.out.println("Select the correct candidate parti number");
            } else{
                try{
                    voteManager.vote(candidate, userManager.getCurrentUsername());
                    System.out.println("You've successfully voted");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void handleUserShowCurrentUserCommand() {
        System.out.println("Current user: " + userManager.getCurrentUsername());
    }

    private static void handleUserLogoutCommand() {
        userManager.logout();
        System.out.println("User has been successfully logged out");
    }


    private static void handleUserRegisterCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        try {
            userManager.register(username, password, false);
            System.out.println("User has been successfully registered");
        } catch (IllegalArgumentException e) {
            System.out.println("User registration failed with: " + e.getMessage());
        }
    }

    private static void handleUserLoginCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        try {
            userManager.login(username, password);
            System.out.println("User has been logged in successfully");

        } catch (IllegalArgumentException e) {
            System.out.println("User login failed with: " + e.getMessage());
        }
    }

    private static void handleAdminCommand(int command) {
        if (command == AdminCommand.ADD) {
            if (userManager.isAdminLoggedIn()) {
                handleCandidateAddCommand();
            } else {
                System.out.println("You have to log in to the system");
            }
        } else if (command == AdminCommand.SHOW) {
            handleCandidateShowCommand();
        } else if (command == AdminCommand.LOGIN) {
            handleCandidateLogInCommand();
        } else if (command == AdminCommand.BACK) {
            mainMenu();
        }

    }

    private static void handleCandidateLogInCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter admin name: ");
        String username = sc.next();
        System.out.print("Enter admin password: ");
        String password = sc.next();

        try {
            userManager.adminLogin(username, password);
            System.out.println("Admin has been logged in successfully");

        } catch (IllegalArgumentException e) {
            System.out.println("Admin login failed with: " + e.getMessage());
        }

    }


    private static void handleCandidateAddCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter firstname of candidate: ");
        String firstname = sc.next();
        System.out.print("Enter lastname of candidate: ");
        String lastname = sc.next();
        System.out.print("Enter the number of parti: ");
        int partiNumber = sc.nextInt();
        try {
            candidatesManager.add(firstname, lastname, partiNumber);
            System.out.println("Candidate has added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Candidate adding failed with: " + e.getMessage());
        }
    }

    private static void handleCandidateShowCommand() {
        Set<Candidate> candidates = candidatesManager.getCandidates();
        if (candidates.isEmpty()) {
            System.out.println("There no registered candidates");
        } else {
            System.out.println("Registered candidates:");
            for (Candidate candidate : candidates) {
                System.out.println(candidate.getPartiNumber() + ") " + candidate.getFullname());
            }
        }

    }
    //add list of users in admin part
}