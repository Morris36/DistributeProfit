package DataBank;


import java.util.ArrayList;

public class Bank {
    private final ArrayList<UnionAgents> unions = new ArrayList<>();

    public void addUnion(UnionAgents unionAgents) {
        unions.add(unionAgents);
    }

    public void removeUnion(UnionAgents unionAgents) {
        unions.remove(unionAgents);
    }

    public ArrayList<UnionAgents> getUnions() {
        return unions;
    }

}
