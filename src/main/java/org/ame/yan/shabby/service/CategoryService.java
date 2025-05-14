package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.CategoryAdminDTO;
import org.ame.yan.shabby.model.dto.CategoryDTO;
import org.ame.yan.shabby.model.dto.CategoryOptionDTO;
import org.ame.yan.shabby.entity.Category;
import org.ame.yan.shabby.model.vo.CategoryVO;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
