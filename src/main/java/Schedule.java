import java.util.ArrayList;

class Schedule {
  String name;
  ArrayList<Integer> times;

  Schedule(String name, ArrayList<Integer> times) {
    super();
    this.name = name;
    this.times = new ArrayList<Integer>();
    this.times.addAll(times);
  }
}