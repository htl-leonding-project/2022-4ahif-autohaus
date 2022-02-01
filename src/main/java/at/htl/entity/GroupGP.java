package at.htl.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class GroupGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @OneToMany
    private List<TeamGP> teams;

    //region Constructor

    public GroupGP(String groupName, List<TeamGP> teams) {
        this.groupName = groupName;
        this.teams = teams;
    }

    public GroupGP() {
    }

    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<TeamGP> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamGP> teams) {
        this.teams = teams;
    }

    public String getGroupMap() {

        String returnString = String.format("""
                map %s {
                %s
                }
                """, this.getGroupName(),
                this.getTeams().stream().map(t -> t.getNameAndAbbr()).collect(Collectors.joining("\n")));

        return returnString;

    }
    //endregion
}
