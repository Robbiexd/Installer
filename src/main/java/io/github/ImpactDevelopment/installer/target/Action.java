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

package io.github.ImpactDevelopment.installer.target;

import io.github.ImpactDevelopment.installer.gui.AppWindow;

import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

public class Action {
    private final String name;
    private final BiConsumer<AppWindow, ActionEvent> action;

    public Action(String name, BiConsumer<AppWindow, ActionEvent> action) {

        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void run(AppWindow app, ActionEvent event) {
        action.accept(app, event);
    }
}
