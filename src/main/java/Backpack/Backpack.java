package Backpack;

import java.util.ArrayList;

/**
 * @author Evgeny Dubov
 */
public class Backpack {
    private double tmpSum = 0;
    private double tmpExpenses;
    private double tmpProfit;
    private double budget;
    private ArrayList<Project> projects;

    public Backpack(double budget, ArrayList<Project> projects) {
        this.budget = budget;
        this.projects = projects;
    }

    public ArrayList<String> hueta() {

            double[] tmpEE = new double[projects.size()];
            double[] tmpPP = new double[projects.size()];
            String[] names = new String[projects.size()];
            for (int i = 0; i < projects.size(); i++) {
                tmpEE[i] = projects.get(i).getExpenses();
                tmpPP[i] = projects.get(i).getProfit();
                names[i] = projects.get(i).getName();
            }
            final int size = (int) (Math.round(budget) + 1);
            double[] Sum = new double[size]; // Массив cуммарной прибыли
            double[] Sum_old = new double[size]; // Массив cуммарной прибыли на предыдущем шаге
            ArrayList<String> arL = new ArrayList<>(size);
            ArrayList<String> arL_old = new ArrayList<>(size);
            for (int v = 0; v < (int) Math.round(budget) + 1; v++) {
                Sum[v] = 0;
                arL.add("");
                arL_old.add("");
            } // начальное   обнуление
            for (int i = 0; i < projects.size(); i++) //поочередно все предметы
            {
                for (int w = 0; w < (int) Math.round(budget) + 1; w++) {
                    Sum_old[w] = Sum[w]; // копирование массивов
                    arL_old.set(w, arL.get(w));
                }
                tmpExpenses = tmpEE[i];
                tmpProfit = tmpPP[i];

                for (double v = 1; v < budget + 1; v++) //постепенно до величины затрат
                {
                    if (tmpExpenses <= v) {
                        tmpSum = Sum_old[(int) ((int) v - Math.round(tmpExpenses))] + tmpProfit; //сум.прибыль если взять i-ый проект
                        if (Sum_old[(int) v] < tmpSum) //Sum_old[v] сум.прибыль если не брать i-ый проект
                        {
                            Sum[(int) v] = tmpSum; //данный проект взят в набор
                            arL.set((int) v, arL_old.get((int) ((int) v - Math.round(tmpExpenses))) + names[i] + "\t");//внесение в список
                        } else {
                            Sum[(int)v] = Sum_old[(int)v];
                            arL.set((int) v,arL_old.get((int) v)); //список остается прежним
                        }
                    } else {
                        Sum[(int)v] = Sum_old[(int)v];
                        arL.set((int) v, arL_old.get((int) v)); //список остается прежним
                    }
                }
            }
            //запись в таблицу с результатами

       // int kol = arL.get(size).length() - 1;
        arL.add(String.valueOf(Sum[size - 1]));
        return arL;
    }

}

