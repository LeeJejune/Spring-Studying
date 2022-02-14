package study.datajpa.repository;

public interface NestedCloseProjections {
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo{
        String getName();
    }
}
