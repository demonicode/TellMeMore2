package com.example.shikhar.tellmemore2;

import android.app.Application;
import android.os.RemoteException;
import android.widget.Toast;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

/**
 * Created by Shikhar on 29-12-2016.
 */

public class testclass extends Application implements BeaconConsumer {
    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();


    }
    public void setUpBeacon(){


        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        new BackgroundPowerSaver(this);
        beaconManager.bind(this);

    }
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Toast.makeText(testclass.this,"did enter",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void didExitRegion(Region region) {
                Toast.makeText(testclass.this,"did exit",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                Toast.makeText(testclass.this,"changed region",Toast.LENGTH_SHORT).show();
            }
        });


        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {    }


    }
}
