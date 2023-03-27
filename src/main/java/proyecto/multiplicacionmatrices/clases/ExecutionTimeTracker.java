package proyecto.multiplicacionmatrices.clases;

public class ExecutionTimeTracker {
    private long totalTime;
    private int numExecutions;

    public ExecutionTimeTracker() {
        totalTime = 0;
        numExecutions = 0;
    }

    public void addExecutionTime(long time) {
        totalTime += time;
        numExecutions++;
    }

    public double getAverageTime() {
        if (numExecutions == 0) {
            return 0;
        }
        return (double) totalTime / numExecutions;
    }
}