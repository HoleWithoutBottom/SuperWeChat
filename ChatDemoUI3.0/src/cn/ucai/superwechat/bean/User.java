package cn.ucai.superwechat.bean;

/**
 * Created by Administrator on 2016/10/13.
 */
public class User {
    private String muserName;
    private String muserNick;
    private int mavatarId;
    private String mavatarPath;
    private String mavatarSuffix;
    private int mavatarType;
    private String mavatarLastUpdateTime;

    public void setMuserName(String muserName) {
        this.muserName = muserName;
    }

    public String getMuserName() {
        return muserName;
    }

    public void setMuserNick(String muserNick) {
        this.muserNick = muserNick;
    }

    public String getMuserNick() {
        return muserNick;
    }

    public void setMavatarId(int mavatarId) {
        this.mavatarId = mavatarId;
    }

    public int getMavatarId() {
        return mavatarId;
    }

    public void setMavatarPath(String mavatarPath) {
        this.mavatarPath = mavatarPath;
    }

    public String getMavatarPath() {
        return mavatarPath;
    }

    public void setMavatarSuffix(String mavatarSuffix) {
        this.mavatarSuffix = mavatarSuffix;
    }

    public String getMavatarSuffix() {
        return mavatarSuffix;
    }

    public void setMavatarType(int mavatarType) {
        this.mavatarType = mavatarType;
    }

    public int getMavatarType() {
        return mavatarType;
    }

    public void setMavatarLastUpdateTime(String mavatarLastUpdateTime) {
        this.mavatarLastUpdateTime = mavatarLastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (muserName != null ? !muserName.equals(that.muserName) : that.muserName != null)
            return false;
        return mavatarLastUpdateTime != null ? mavatarLastUpdateTime.equals(that.mavatarLastUpdateTime) : that.mavatarLastUpdateTime == null;

    }

    @Override
    public int hashCode() {
        int result = muserName != null ? muserName.hashCode() : 0;
        result = 31 * result + (mavatarLastUpdateTime != null ? mavatarLastUpdateTime.hashCode() : 0);
        return result;
    }

    public String getMavatarLastUpdateTime() {
        return mavatarLastUpdateTime;
    }

    public User() {
    }


}
