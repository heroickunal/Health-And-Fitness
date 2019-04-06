package azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie;

public class calorie {
    int age;
    int weight;

    public double calculate()
    {
        return((age*0.2017-weight*0.09036+168*0.6309-55.0969)*0.066/4.184);
    }
    public calorie(int a,int b)
    {
        age=a;
        weight=b;
    }
}
