package icpc.njust.test.table;

import javax.persistence.*;

/**
 * Created by DELL on 2018/12/22.
 */
@Entity
@Table(name = "warninginfo", schema = "classroom", catalog = "classroom")
public class WarninginfoEntity {
    private String warningid;
    private String id;
    private String classid;
    private String classcnt;
    private String warningcontent;
    private String time;

    @Id
    @Column(name = "warningid")
    public String getWarningid() {
        return warningid;
    }

    public void setWarningid(String warningid) {
        this.warningid = warningid;
    }

    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "classcnt")
    public String getClasscnt() {
        return classcnt;
    }

    public void setClasscnt(String classcnt) {
        this.classcnt = classcnt;
    }

    @Basic
    @Column(name = "warningcontent")
    public String getWarningcontent() {
        return warningcontent;
    }

    public void setWarningcontent(String warningcontent) {
        this.warningcontent = warningcontent;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarninginfoEntity that = (WarninginfoEntity) o;

        if (warningid != null ? !warningid.equals(that.warningid) : that.warningid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;
        if (classcnt != null ? !classcnt.equals(that.classcnt) : that.classcnt != null) return false;
        if (warningcontent != null ? !warningcontent.equals(that.warningcontent) : that.warningcontent != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = warningid != null ? warningid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (classid != null ? classid.hashCode() : 0);
        result = 31 * result + (classcnt != null ? classcnt.hashCode() : 0);
        result = 31 * result + (warningcontent != null ? warningcontent.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
