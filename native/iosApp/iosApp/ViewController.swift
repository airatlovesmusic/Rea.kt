//
//  ViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/02/2020.
//  Copyright © 2020  Airat Khalilov. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController {
    
    @IBOutlet weak var label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = "Hello from " + GetNameKt.getName()
    }
    
}

