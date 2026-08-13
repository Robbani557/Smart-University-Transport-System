package model;

public class Student {

 

    private String studentId;
    private String name;
    private String department;
    private Route route;

 

    public Student(String studentId,
                   String name,
                   String department,
                   Route route) {

        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.route = route;
    }



    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Route getRoute() {
        return route;
    }

   

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {

        return studentId
                + " - "
                + name
                + " - "
                + department
                + " - Route: "
                + route.getRouteName();
    }
}