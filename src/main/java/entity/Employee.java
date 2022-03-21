package entity;

import java.util.List;

/**
 * @author: Feng.Lee
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Employee {

    public int happy;

    public List<Employee> nexts;

    public Employee(int happy, List<Employee> nexts) {
        this.happy = happy;
        this.nexts = nexts;
    }

}
