package com.zyrj.usermanagement.domain;

/**
 * @auther ccm
 * @date 16:27
 */
public class Member {
    private Integer gradeId;
    private String memberType;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "gradeId=" + gradeId +
                ", memberType='" + memberType + '\'' +
                '}';
    }
}
