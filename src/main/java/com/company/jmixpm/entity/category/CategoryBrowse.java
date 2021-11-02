package com.company.jmixpm.entity.category;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Category;

@UiController("Category.browse")
@UiDescriptor("category-browse.xml")
@LookupComponent("categoriesTable")
public class CategoryBrowse extends StandardLookup<Category> {
}