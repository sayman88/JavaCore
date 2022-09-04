package work1;

public class Team {
    public static int MAX_TEAM_SIZE = 4;

    private String name;
    private TeamMember[] teamMembers;

    public Team(String name,
                String memberName1, int memberMaxCourseComplexity1,
                String memberName2, int memberMaxCourseComplexity2,
                String memberName3, int memberMaxCourseComplexity3,
                String memberName4, int memberMaxCourseComplexity4 ) {
        this.name = name;
        this.teamMembers = new TeamMember[MAX_TEAM_SIZE];

        this.teamMembers[0] = new TeamMember(memberName1, memberMaxCourseComplexity1);
        this.teamMembers[1] = new TeamMember(memberName2, memberMaxCourseComplexity2);
        this.teamMembers[2] = new TeamMember(memberName3, memberMaxCourseComplexity3);
        this.teamMembers[3] = new TeamMember(memberName4, memberMaxCourseComplexity4);
    }

    public String getName() {
        return name;
    }

    public TeamMember[] getTeamMembers() {
        return teamMembers;
    }

    public void showResults() {
        showAllResults(false);
    }

    public void showPassedResults() {
        showAllResults(true);
    }

    private void showAllResults(boolean justPassed) {
        for(int i = 0; i < Team.MAX_TEAM_SIZE; i++) {
            TeamMember member = teamMembers[i];
            if (member.isHasPassed()) {
                System.out.println("Member " + member.getName() + " passed the test!");
            } else {
                if (!justPassed) {
                    System.out.println("Member " + member.getName() + " not passed the test!");
                }
            }
        }
    }

}