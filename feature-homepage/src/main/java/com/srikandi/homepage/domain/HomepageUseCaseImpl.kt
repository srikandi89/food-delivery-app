package com.srikandi.homepage.domain

import com.srikandi.homepage.data.HomepageRepository
import com.srikandi.homepage.data.mapper.HomepageDataMapper.getShowcaseMapper
import com.srikandi.homepage.domain.model.HomepageCategoryDto
import com.srikandi.homepage.domain.model.HomepageFilterDto
import com.srikandi.homepage.domain.model.HomepageImageSlideDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.uikit.imageslider.ImageSliderDto
import io.reactivex.Observable

class HomepageUseCaseImpl(
    private val repository: HomepageRepository
) : HomepageUseCase {
    override fun getSliderImages(): Observable<List<ImageSliderDto>> {
        return repository.getSliderImages().map {
            getShowcaseMapper().generateImageSlideList(it)
        }
    }

    override fun getProductList(): Observable<HomepageProductListDto> {
        return repository.getProductList().map {
            getShowcaseMapper().generateProductList(it)
        }
    }

    override fun getFilterList(): Observable<List<HomepageFilterDto>> {
        return repository.getFilterList().map {
            getShowcaseMapper().generateFilterList(it)
        }
    }

    override fun getCategoryList(): Observable<List<HomepageCategoryDto>> {
        return repository.getCategoryList().map {
            getShowcaseMapper().generateCategoryList(it)
        }
    }

}