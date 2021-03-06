/*
 * This file is part of Impact Installer.
 *
 * Copyright (C) 2019  ImpactDevelopment and contributors
 *
 * See the CONTRIBUTORS.md file for a list of copyright holders
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package io.github.ImpactDevelopment.installer.setting.settings;

import io.github.ImpactDevelopment.installer.impact.ImpactVersion;
import io.github.ImpactDevelopment.installer.impact.ImpactVersionDisk;
import io.github.ImpactDevelopment.installer.impact.ImpactVersionReleased;
import io.github.ImpactDevelopment.installer.impact.ImpactVersions;
import io.github.ImpactDevelopment.installer.setting.ChoiceSetting;
import io.github.ImpactDevelopment.installer.setting.InstallationConfig;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum ImpactVersionSetting implements ChoiceSetting<ImpactVersion> {
    INSTANCE;

    @Override
    public List<ImpactVersion> getPossibleValues(InstallationConfig config) {
        String mcVersion = config.getSettingValue(MinecraftVersionSetting.INSTANCE);
        return ImpactVersions.getAllVersions().stream()
                .filter(config.getSettingValue(InstallationModeSetting.INSTANCE)::supports)
                .filter(version -> mcVersion.equals(version.mcVersion))
                .sorted(Comparator.comparing((ImpactVersionReleased version) -> version.impactVersion).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public boolean validSetting(InstallationConfig config, ImpactVersion value) {
        if (ChoiceSetting.super.validSetting(config, value)) {
            return true;
        }
        return value != null && value instanceof ImpactVersionDisk;
    }

    @Override
    public String displayName(InstallationConfig config, ImpactVersion option) {
        String ret = option.impactVersion;
        if (getPossibleValues(config).indexOf(option) == 0) { // hitting nae nae on the O(n^2)
            ret += " (latest)";
        }
        return ret;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
