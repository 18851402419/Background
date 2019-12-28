package myUnit;

/**
 * 课程详细信息实体类
 *
 */
public class ClassCondition {
    /**
     * 各个成员变量的getter和setter方法
     *
     */
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassRegisterState() {
        return classRegisterState;
    }

    public void setClassRegisterState(String classRegisterState) {
        this.classRegisterState = classRegisterState;
    }

    public String getClassNewTime() {
        return classNewTime;
    }

    public void setClassNewTime(String classNewTime) {
        this.classNewTime = classNewTime;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    private String classId;
    private String classRegisterState;  // 课程签到状态（学生才有）
    private String classNewTime;    // 课程创建时间（teacher）
    private String enrollment;  // 已选课人数（teacher）
    private String maxNum;  // 课程最大容量（teacher）
}
