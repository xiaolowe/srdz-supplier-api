package cn.org.citycloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.org.citycloud.entity.RegionInfo;


public interface RegionInfoDao extends PagingAndSortingRepository<RegionInfo, Integer> ,JpaRepository<RegionInfo, Integer>, JpaSpecificationExecutor<RegionInfo> {

	List<RegionInfo> findByRegionLevel(int regionlevel);
	
	@Query(value="select * from region_info a where a.region_code like ?1% and a.region_level = ?2", nativeQuery = true)
	List<RegionInfo> findCityOrAreaRegionCode(int regioncode, int regionlevel);
	
	
	
}
