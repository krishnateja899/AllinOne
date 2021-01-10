package com.example.allinone.RecyclerViews.HEighthRecView;

public class AssignFragData {
    String subtaskname;
    String xpcn;
    String xpts;
    String ffv;
    String vehicle_no;
    String route;// origin
    String touch;
    String dep;
    String at;
    String dt;
    String tt;
    String timeTaken;
    String ht;
    String status;
    String destination;
    String tripId;

    public AssignFragData() {

    }

    public AssignFragData(String subtaskname, String xpcn, String xpts, String ffv, String vehicle_no, String route, String touch, String dep, String at, String dt, String tt, String timeTaken, String ht, String status, String destination, String tripId) {
        this.subtaskname = subtaskname;
        this.xpcn = xpcn;
        this.xpts = xpts;
        this.ffv = ffv;
        this.vehicle_no = vehicle_no;
        this.route = route;
        this.touch = touch;
        this.dep = dep;
        this.at = at;
        this.dt = dt;
        this.tt = tt;
        this.timeTaken = timeTaken;
        this.ht = ht;
        this.status = status;
        this.destination = destination;
        this.tripId = tripId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSubtaskname() {
        return subtaskname;
    }

    public void setSubtaskname(String subtaskname) {
        this.subtaskname = subtaskname;
    }

    public String getXpcn() {
        return xpcn;
    }

    public void setXpcn(String xpcn) {
        this.xpcn = xpcn;
    }

    public String getXpts() {
        return xpts;
    }

    public void setXpts(String xpts) {
        this.xpts = xpts;
    }

    public String getFfv() {
        return ffv;
    }

    public void setFfv(String ffv) {
        this.ffv = ffv;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getHt() {
        return ht;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }
}
