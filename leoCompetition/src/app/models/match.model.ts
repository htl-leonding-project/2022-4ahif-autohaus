import { Team } from "./team.model";
import { Tournament } from "./tournament.model";

export interface Match {

    id: number,
    team1: Team,
    team2: Team,
    pointsTeam1: number,
    pointsTeam2: number

}
