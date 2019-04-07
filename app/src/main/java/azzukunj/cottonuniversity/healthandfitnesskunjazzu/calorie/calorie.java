package azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie;

public class calorie {
    double age;
    double weight;

    public double calculate(double a, double b) {

        return (((age * 0.2017 - weight * 0.09036 + 168 * 0.6309 - 55.0969) * 0.066) / 4.184-0.2);
    }
}
