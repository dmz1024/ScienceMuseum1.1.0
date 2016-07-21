package com.shoudukejiguan.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.enums.PathPlanningStrategy;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.RouteOverLay;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.shoudukejiguan.www.R;
import com.shoudukejiguan.www.manager.MapManager;
import com.shoudukejiguan.www.view.MyToast;

import java.util.ArrayList;

/**
 * Created by dengmingzhi on 16/7/21.
 */
public class AMapFragment extends BaseFragment implements AMapNaviListener {
    MapView mMapView;
    AMap aMap;
    AMapNavi aMapNavi;
    // 规划线路
    RouteOverLay mRouteOverLay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView = new MapView(getActivity());
        mMapView.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        return mMapView;
    }


    private void initData() {
        initAmap();
//        aMapNavi = AMapNavi.getInstance(getContext());
//        aMapNavi.addAMapNaviListener(this);
//        aMapNavi.setEmulatorNaviSpeed(150);
//        mRouteOverLay = new RouteOverLay(aMap, null);

        LatLng lng = new LatLng(39.970341, 116.393275);
        //设置中心点和缩放比例
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(lng));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
//        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.icon_map);
//        markerOptions.icon(icon);
        aMap.addMarker(new MarkerOptions().position(lng).perspective(true).title("")).showInfoWindow();
//        aMap.setTrafficEnabled(false);//显示交通情况
    }


    private void initAmap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    View v = View.inflate(getContext(), R.layout.item_map_info, null);
                    ImageView imageView = (ImageView) v.findViewById(R.id.iv_go);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final MapManager mapManager = new MapManager(getContext());
                            mapManager.setOnLocationListener(new MapManager.OnLocationListener() {
                                @Override
                                public void location(double latitude, double longitude) {
//                                    Log.d("我的位置：", latitude + "---" + longitude);
                                    mapManager.stopLocation();
                                    ArrayList<NaviLatLng> mStartPoints = new ArrayList<NaviLatLng>();
                                    ArrayList<NaviLatLng> mEndPoints = new ArrayList<NaviLatLng>();

                                    NaviLatLng mNaviStart = new NaviLatLng(latitude, longitude);
                                    NaviLatLng mNaviEnd = new NaviLatLng(39.970341, 116.393275);

                                    mStartPoints.add(mNaviStart);
                                    mEndPoints.add(mNaviEnd);
                                    boolean isSuccess = aMapNavi.calculateDriveRoute(mStartPoints,
                                            mEndPoints, null, PathPlanningStrategy.DRIVING_DEFAULT);
                                    if (!isSuccess) {
                                        MyToast.showToast("路线计算失败,检查参数情况");
                                    }

                                }

                                @Override
                                public void locationErr(String errInfo) {
//                                    Log.d("我的位置：", errInfo);
                                    mapManager.stopLocation();
                                    MyToast.showToast(errInfo);
                                }
                            });
                        }
                    });
                    return v;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteSuccess() {
        AMapNaviPath naviPath = aMapNavi.getNaviPath();
        if (naviPath == null) {
            return;
        }
        // 获取路径规划线路，显示到地图上
        mRouteOverLay.setAMapNaviPath(naviPath);
        mRouteOverLay.addToMap();
    }

    @Override
    public void onCalculateRouteFailure(int i) {
        MyToast.showToast("路径规划出错" + i);
    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }
}
