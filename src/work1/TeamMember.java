package work1;

public class TeamMember {
    private String name;
    private int maxCourseComplexity;
    private boolean hasPassed;

    public TeamMember(String name, int maxCourseComplexity) {
        this.name = name;
        this.hasPassed = false;
        this.maxCourseComplexity = maxCourseComplexity;
    }

    public String getName() {
        return name;
    }

    public int getMaxCourseComplexity() {
        return maxCourseComplexity;
    }

    public boolean isHasPassed() {
        return hasPassed;
    }

    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }
}