package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (Section)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:53:10
 */
@Data
@TableName("section")
public class Section extends BaseEntity<Section> {
    //The uuid of the course offering this section belongs to.
    private String courseOfferingUuid;

    //The type of section (lecture, discussion, lab, etc.)
    private String sectionType;

    //The number of this section.
    private Long number;

    //The uuid of the room this section met at.
    private String roomUuid;

    //The uuid of the schedule that this section adhered to.
    private String scheduleUuid;

}
