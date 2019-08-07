package com.zyrj.usermanagement.domain;

import java.io.Serializable;

/**
 * @auther ccm
 * @date 16:27
 */
public class Member implements Serializable {
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
