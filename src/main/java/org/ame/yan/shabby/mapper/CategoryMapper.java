package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.CategoryAdminDTO;
import org.ame.yan.shabby.model.dto.CategoryDTO;
import org.ame.yan.shabby.entity.Category;
import org.ame.yan.shabby.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> listCategories();

    List<CategoryAdminDTO> listCategoriesAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
