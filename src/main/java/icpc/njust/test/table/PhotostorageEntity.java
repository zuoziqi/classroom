package icpc.njust.test.table;

import javax.persistence.*;

/**
 * Created by DELL on 2018/12/22.
 */
@Entity
@Table(name = "photostorage", schema = "", catalog = "classroom")
public class PhotostorageEntity {
    private String id;
    private String imageBase64;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image_base64")
    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotostorageEntity that = (PhotostorageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (imageBase64 != null ? !imageBase64.equals(that.imageBase64) : that.imageBase64 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageBase64 != null ? imageBase64.hashCode() : 0);
        return result;
    }
}
