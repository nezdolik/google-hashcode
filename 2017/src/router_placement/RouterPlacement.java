/**
 *  1. Router needs to be connected to backbone:
 *      there should exist a path P from backbone cell to router cell, shortest path reduces backbone cost.
 *
 *  2. We want to minimize cost, place as few routers as possible and maximise their coverage area
 *    find Sbest(cost,cov_area) from {S} such that, for every Sany in {S}:
 *          Sbest.cost <= Sany.cost AND Sbest.cov_area >= Sany.cov_area ==> is this right assumption?
 *
 *  3. Fow every router placement we have max budget B and estimate accumulated cost C(Pr,Pb),
 *    total optimal cost Ct would be:
 *     max(B - sum(Ci)), therefore need to minimise sum(Ci), Ci in {C}, i:{0, N-1}, where N - amount of router placements,
 *     to minimize individual cost we need to minimize number of routers + select shorted path to backbone
 *
 *  4. Does this mean that we need to find arrangement so that all target cells are covered and
 *     cost is optimal?
 *
 *  5. Does reducing intersection areas lead to optimal solution?
 *
 *  6. If aiming to optimise backbone cost, would it lead to optimal solution? Looks like it would conflict with 5.
 *
 *
 *
 *
 * **/
package router_placement;

public class RouterPlacement{

    public static


    final class Grid{
        private CellType[][] grid;

    }

    enum CellType{
        WALL('#'),
        BACKBONE('b'),
        VOID('-'),
        TARGET('.'),
        ROUTER('r');

        CellType(char code){
            this.code = code;
        }

        private char code;
    }
}