/*  Copyright (C) 2022 José Rebelo

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package nodomain.freeyourgadget.gadgetbridge.service.devices.sony.headphones.protocol.impl.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nodomain.freeyourgadget.gadgetbridge.devices.sony.headphones.prefs.ButtonModes;
import nodomain.freeyourgadget.gadgetbridge.impl.GBDevice;
import nodomain.freeyourgadget.gadgetbridge.service.devices.sony.headphones.protocol.impl.v2.SonyProtocolImplV2;

public class SonyProtocolImplV3 extends SonyProtocolImplV2 {
    private static final Logger LOG = LoggerFactory.getLogger(SonyProtocolImplV3.class);

    public SonyProtocolImplV3(final GBDevice device) {
        super(device);
    }

    @Override
    protected ButtonModes.Mode decodeButtonMode(final byte b) {
        switch (b) {
            case (byte) 0xff:
                return ButtonModes.Mode.OFF;
            case (byte) 0x35:  // Seems to be the only one that differs?
                return ButtonModes.Mode.AMBIENT_SOUND_CONTROL;
            case (byte) 0x20:
                return ButtonModes.Mode.PLAYBACK_CONTROL;
            case (byte) 0x10:
                return ButtonModes.Mode.VOLUME_CONTROL;
        }

        return null;
    }

    @Override
    protected byte encodeButtonMode(final ButtonModes.Mode buttonMode) {
        switch (buttonMode) {
            case OFF:
                return (byte) 0xff;
            case AMBIENT_SOUND_CONTROL:
                return (byte) 0x35; // Seems to be the only one that differs?
            case PLAYBACK_CONTROL:
                return (byte) 0x20;
            case VOLUME_CONTROL:
                return (byte) 0x10;
        }

        throw new IllegalArgumentException("Unknown button mode " + buttonMode);
    }
}
