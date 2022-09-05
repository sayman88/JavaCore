package work1;

public class Course {
    private int[] complexity;

    public Course(int[] complexity) {
        this.complexity = complexity;
    }

    public void doIt(Team team) {
        TeamMember[] members = team.getTeamMembers();
        for(int i = 0; i < Team.MAX_TEAM_SIZE; i++) {
            TeamMember member = members[i];
            member.setHasPassed(true);
            for (int j = 0; i < complexity.length -1; j++) {
                if(member.getMaxCourseComplexity() < complexity[j]) {
                    member.setHasPassed(false);
                    break;
                }
            }
        }
    }
}

