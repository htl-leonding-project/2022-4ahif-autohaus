package at.htl.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "T_GroupGP")
public class GroupGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "G_ID")
    private Long id;
    @Column(name = "G_GroupName")
    private String groupName;
    @OneToMany
    @JoinColumn(name = "G_Teams")
    private List<Team> teams;

    //region Constructor

    public GroupGP(String groupName, List<Team> teams) {
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
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
