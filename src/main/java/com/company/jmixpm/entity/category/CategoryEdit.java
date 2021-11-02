package com.company.jmixpm.entity.category;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Category;

@UiController("Category.edit")
@UiDescriptor("category-edit.xml")
@EditedEntityContainer("categoryDc")
public class CategoryEdit extends StandardEditor<Category> {
}