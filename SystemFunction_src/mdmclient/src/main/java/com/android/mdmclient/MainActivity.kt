package com.android.mdmclient

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.android.mdmclient.databinding.ActivityMainBinding
import com.android.mdmsdk.addDisallowedUninstallPackages
import com.android.mdmsdk.addForbiddenInstallApp
import com.android.mdmsdk.addInstallPackageTrustList
import com.android.mdmsdk.addPersistentApp
import com.android.mdmsdk.bind
import com.android.mdmsdk.change
import com.android.mdmsdk.disableSms
import com.android.mdmsdk.getDisallowedUninstallPackageList
import com.android.mdmsdk.getForbiddenInstallAppList
import com.android.mdmsdk.getInstallPackageTrustList
import com.android.mdmsdk.getPersistentApp
import com.android.mdmsdk.getSuperWhiteListForSystem
import com.android.mdmsdk.isBackKeyDisable
import com.android.mdmsdk.isBind
import com.android.mdmsdk.isBluetoothDisabled
import com.android.mdmsdk.isCallPhoneDisabled
import com.android.mdmsdk.isCameraDisable
import com.android.mdmsdk.isDataConnectivityDisabled
import com.android.mdmsdk.isGPSDisable
import com.android.mdmsdk.isHomeKeyDisable
import com.android.mdmsdk.isHotSpotDisabled
import com.android.mdmsdk.isInstallDisabled
import com.android.mdmsdk.isMicrophoneDisable
import com.android.mdmsdk.isNavigationBarDisable
import com.android.mdmsdk.isRecentKeyDisable
import com.android.mdmsdk.isRestoreFactoryDisable
import com.android.mdmsdk.isScreenShot
import com.android.mdmsdk.isSmsDisable
import com.android.mdmsdk.isStatusBarDisable
import com.android.mdmsdk.isSystemUpdateDisabled
import com.android.mdmsdk.isTFCardDisabled
import com.android.mdmsdk.isUSBDataDisabled
import com.android.mdmsdk.isUninstallDisabled
import com.android.mdmsdk.isWifiDisabled
import com.android.mdmsdk.removeDisallowedUninstallPackages
import com.android.mdmsdk.removeForbiddenInstallApp
import com.android.mdmsdk.removeInstallPackageTrustList
import com.android.mdmsdk.removePersistentApp
import com.android.mdmsdk.removeSuperWhiteListForSystem
import com.android.mdmsdk.setBackKeyDisable
import com.android.mdmsdk.setBluetoothDisable
import com.android.mdmsdk.setCallPhoneDisabled
import com.android.mdmsdk.setCameraDisable
import com.android.mdmsdk.setDataConnectivityDisabled
import com.android.mdmsdk.setGPSDisabled
import com.android.mdmsdk.setHomeKeyDisabled
import com.android.mdmsdk.setHotSpotDisabled
import com.android.mdmsdk.setInstallDisabled
import com.android.mdmsdk.setMicrophoneDisable
import com.android.mdmsdk.setNavigationBarDisable
import com.android.mdmsdk.setRecentKeyDisable
import com.android.mdmsdk.setRestoreFactoryDisabled
import com.android.mdmsdk.setScreenShotDisable
import com.android.mdmsdk.setStatusBarDisable
import com.android.mdmsdk.setSuperWhiteListForSystem
import com.android.mdmsdk.setSystemUpdateDisabled
import com.android.mdmsdk.setTFCardDisabled
import com.android.mdmsdk.setUSBDataDisabled
import com.android.mdmsdk.setUninstallDisabled
import com.android.mdmsdk.setWifiDisabled
import com.android.mdmsdk.unbind

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind(this)
        binding.home.change {
            if (isBind()) setHomeKeyDisabled(it) else {
                Toast.makeText(
                    this, "未绑定,请检查com.android.systemfunction是否安装", Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.recent.change { setRecentKeyDisable(it) }
        binding.back.change { setBackKeyDisable(it) }
        binding.navigation.change { setNavigationBarDisable(it) }
        binding.status.change { setStatusBarDisable(it) }
        binding.usbData.change { setUSBDataDisabled(it) }
        binding.bluetooth.change { setBluetoothDisable(it) }
        binding.wifi.change { setWifiDisabled(it) }
        binding.data.change { setDataConnectivityDisabled(it) }
        binding.gps.change { setGPSDisabled(it) }
        binding.camera.change { setCameraDisable(it) }
        binding.microphone.change { setMicrophoneDisable(it) }
        binding.screenShot.change { setScreenShotDisable(it) }
        binding.tfCard.change { setTFCardDisabled(it) }
        binding.phoneCall.change { setCallPhoneDisabled(it) }
        binding.hotSpot.change { setHotSpotDisabled(it) }
        binding.sms.change { disableSms(it) }
        binding.restoreFactory.change { setRestoreFactoryDisabled(it) }
        binding.systemUpdate.change { setSystemUpdateDisabled(it) }
        binding.disableInstall.change { setInstallDisabled(it) }
        binding.disableUninstall.change { setUninstallDisabled(it) }
        val addList = listOf(
            "com.guoshi.httpcanary", "com.zhihu.android", "com.coolapk.market"
        )
        val removeList = listOf("com.guoshi.httpcanary")
        binding.disInstall.change {
            if (it) addForbiddenInstallApp(addList)
            else removeForbiddenInstallApp(removeList)
        }
        binding.getDisInstall.setOnClickListener {
            binding.textDisInstall.text = "禁止安装列表 ${getForbiddenInstallAppList()}"
        }

        binding.install.change {
            if (it) addInstallPackageTrustList(addList)
            else removeInstallPackageTrustList(removeList)
        }
        binding.getInstall.setOnClickListener {
            binding.textInstall.text = "允许安装列表 ${getInstallPackageTrustList()}"
        }

        binding.disUninstall.change {
            if (it) addDisallowedUninstallPackages(addList)
            else removeDisallowedUninstallPackages(removeList)
        }
        binding.getDisUninstall.setOnClickListener {
            binding.textUninstall.text = "禁止卸载列表 ${getDisallowedUninstallPackageList()}"
        }

        binding.persistent.change {
            if (it) addPersistentApp(addList)
            else removePersistentApp(removeList)
        }
        binding.getPersistent.setOnClickListener {
            binding.textPersistent.text = "保活列表 ${getPersistentApp()}"
        }

        binding.superWhite.change {
            if (it) setSuperWhiteListForSystem(addList)
            else removeSuperWhiteListForSystem(removeList)
        }
        binding.getSuperWhite.setOnClickListener {
            binding.textSuper.text = "受信任列表 ${getSuperWhiteListForSystem()}"
        }

        delayed(1000) { runOnUiThread { updateUI() } }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        if (isBind()) {
            binding.home.isChecked = isHomeKeyDisable()
            binding.recent.isChecked = isRecentKeyDisable()
            binding.back.isChecked = isBackKeyDisable()
            binding.navigation.isChecked = isNavigationBarDisable()
            binding.status.isChecked = isStatusBarDisable()
            binding.usbData.isChecked = isUSBDataDisabled()
            binding.bluetooth.isChecked = isBluetoothDisabled()
            binding.wifi.isChecked = isWifiDisabled()
            binding.data.isChecked = isDataConnectivityDisabled()
            binding.gps.isChecked = isGPSDisable()
            binding.camera.isChecked = isCameraDisable()
            binding.microphone.isChecked = isMicrophoneDisable()
            binding.screenShot.isChecked = isScreenShot()
            binding.tfCard.isChecked = isTFCardDisabled()
            binding.phoneCall.isChecked = isCallPhoneDisabled()
            binding.hotSpot.isChecked = isHotSpotDisabled()
            binding.sms.isChecked = isSmsDisable()
            binding.restoreFactory.isChecked = isRestoreFactoryDisable()
            binding.systemUpdate.isChecked = isSystemUpdateDisabled()
            binding.disableInstall.isChecked = isInstallDisabled()
            binding.disableUninstall.isChecked = isUninstallDisabled()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbind(this)
    }

    private fun delayed(delay: Long, block: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(delay) { block() }
    }
}